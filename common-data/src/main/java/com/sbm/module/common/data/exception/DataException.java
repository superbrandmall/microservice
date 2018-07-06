package com.sbm.module.common.data.exception;

import com.sbm.module.common.exception.IBusinessCode;

public enum DataException implements IBusinessCode {

	D0001("D0001", "操作码不正确。operate:{0}", "操作码不正确");

	private String clazz;

	private String code;

	private String message;

	private String customerMessage;

	DataException(String code, String message, String customerMessage) {
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
