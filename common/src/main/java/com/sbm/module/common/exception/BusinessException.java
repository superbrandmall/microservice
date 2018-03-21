package com.sbm.module.common.exception;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.util.FormatUtil;
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

	/********** 转换为BusinessException **********/
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

	/********* 构造异常 **********/
	public BusinessException(IBusinessCode businessCode) {
		this(businessCode, null, businessCode.getData());
	}

	public BusinessException(IBusinessCode businessCode, Object[] objArr) {
		this(businessCode, null, objArr);
	}

	public BusinessException(IBusinessCode businessCode, Throwable e) {
		this(businessCode, e, null);
	}

	public BusinessException(IBusinessCode businessCode, Throwable e, Object data) {
		this(businessCode, e, data, null);
	}

	public BusinessException(IBusinessCode businessCode, Throwable e, Object[] objArr) {
		this(businessCode, e, null, objArr);
	}

	public BusinessException(IBusinessCode businessCode, Throwable e, Object data, Object[] objArr) {
		super(e);
		this.clazz = businessCode.getClazz();
		this.code = businessCode.getCode();
		this.message = FormatUtil.messageFormat(businessCode.getMessage(), objArr);
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
