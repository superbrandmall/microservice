package com.sbm.module.common.exception;

import lombok.Data;

import java.text.MessageFormat;

@Data
public class BusinessException extends RuntimeException implements IBusinessCode {

	private String code;

	private String message;

	private String customerMessage;

	private Object data;

	/**
	 * 转换为BusinessException
	 *
	 * @param businessCode
	 * @param e
	 * @return
	 */
	public static BusinessException convert(IBusinessCode businessCode, Exception e) {
		// 如果不是BusinessException则转换
		if (!(e instanceof BusinessException)) {
			e = new BusinessException(businessCode, e);
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

	public BusinessException(IBusinessCode businessCode, Throwable e) {
		super(e);
		this.code = businessCode.getCode();
		this.message = businessCode.getMessage();
		this.customerMessage = businessCode.getCustomerMessage();
	}

	public BusinessException(IBusinessCode businessCode, Object[] objArr, Throwable e) {
		super(e);
		this.code = businessCode.getCode();
		this.message = messageFormat(businessCode.getMessage(), objArr);
		this.customerMessage = businessCode.getCustomerMessage();
	}

}
