package com.sbm.module.common.exception;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.text.MessageFormat;

@Data
public class BusinessException extends RuntimeException implements IBusinessCode {

	private String clazz;

	private String code;

	private String message;

	private String customerMessage;

	private Object data;

	public static final String LOG_MESSAGE = "clazz: {0}, code: {1}, message: {2}, data: {3}";

	/**
	 * 转换为BusinessException
	 *
	 * @param businessCode
	 * @param e
	 * @return
	 */
	public static BusinessException convert(IBusinessCode businessCode, Exception e) {
		return convert(businessCode, e, null);
	}

	public static BusinessException convert(IBusinessCode businessCode, Exception e, Object data) {
		return convert(businessCode, e, data, null);
	}

	public static BusinessException convert(IBusinessCode businessCode, Exception e, Object data, Object[] objArr) {
		// 如果不是BusinessException则转换
		if (!(e instanceof BusinessException)) {
			e = new BusinessException(businessCode, e, data, objArr);
		}
		return (BusinessException) e;
	}

	/**
	 * 格式化参数
	 *
	 * @param objArr
	 * @return
	 */
	public static String messageFormat(String message, Object[] objArr) {
		if (null == objArr) {
			return message;
		}
		Object[] temp = new Object[objArr.length];
		for (int i = 0; i < objArr.length; i++) {
			// 因为坑爹的MessageFormat会自动格式化数字，所以转成String不格式化
			if (objArr[i] instanceof Integer || objArr[i] instanceof Long) {
				temp[i] = String.valueOf(objArr[i]);
			} else {
				temp[i] = objArr[i];
			}
		}
		return MessageFormat.format(message, temp);
	}

	/**
	 * 构造异常
	 *
	 * @param businessCode
	 * @param e
	 */
	public BusinessException(IBusinessCode businessCode, Throwable e) {
		this(businessCode, e, null);
	}

	public BusinessException(IBusinessCode businessCode, Throwable e, Object data) {
		this(businessCode, e, data, null);
	}

	public BusinessException(IBusinessCode businessCode, Throwable e, Object data, Object[] objArr) {
		super(e);
		this.clazz = businessCode.getClazz();
		this.code = businessCode.getCode();
		this.message = messageFormat(businessCode.getMessage(), objArr);
		this.customerMessage = businessCode.getCustomerMessage();
		this.data = data;
	}

	/**
	 * 打印日志信息
	 *
	 * @return
	 */
	public String getLogMessage() {
		return MessageFormat.format(LOG_MESSAGE, clazz, code, message, JSON.toJSON(data));
	}

}
