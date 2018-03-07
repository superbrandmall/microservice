package com.sbm.module.common.authorization.exception;

import com.sbm.module.common.exception.IBusinessCode;

public enum MethodCode implements IBusinessCode {

	M0001("M0001", "一个@RequestMapping有且只能有一个method, clazz: {0}, method: {1}", "生成api失败"),
	M0002("M0002", "一个@RequestMapping有且只能有一个value, clazz: {0}, method: {1}", "生成api失败");


	private String clazz;

	private String code;

	private String message;

	private String customerMessage;

	MethodCode(String code, String message, String customerMessage) {
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
