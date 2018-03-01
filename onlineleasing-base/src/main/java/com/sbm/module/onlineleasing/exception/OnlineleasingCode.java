package com.sbm.module.onlineleasing.exception;

import com.sbm.module.common.exception.IBusinessCode;

public enum OnlineleasingCode implements IBusinessCode {

	B0001("B0001", "生成流水号出错:{0}", "生成流水号出错");


	private String clazz;

	private String code;

	private String message;

	private String customerMessage;

	OnlineleasingCode(String code, String message, String customerMessage) {
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
