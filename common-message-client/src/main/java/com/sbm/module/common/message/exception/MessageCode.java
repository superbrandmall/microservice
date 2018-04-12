package com.sbm.module.common.message.exception;

import com.sbm.module.common.exception.IBusinessCode;

public enum MessageCode implements IBusinessCode {

	/******************** 方法异常 ********************/
	T0001("T0001", "模板处理失败:{0}", "模板处理失败");


	private String clazz;

	private String code;

	private String message;

	private String customerMessage;

	MessageCode(String code, String message, String customerMessage) {
		this.clazz = this.getClass().getName();
		this.code = code;
		this.message = message;
		this.customerMessage = customerMessage;
	}

	@Override
	public String getClazz() {
		return clazz;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String getCustomerMessage() {
		return customerMessage;
	}

	@Override
	public Object getData() {
		return null;
	}
}
