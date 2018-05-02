package com.sbm.module.common.authorization.api.verificationcode.exception;

import com.sbm.module.common.exception.IBusinessCode;

public enum VerificationCodeErrorCode implements IBusinessCode {

	VC0001("VC0001", "验证码不存在或已失效:{0}", "验证码不存在或已失效"),
	VC0002("VC0002", "验证码不正确:{0}", "验证码不正确"),
	VC0003("VC0003", "关键词不匹配:{0}", "关键词不匹配"),;


	private String clazz;

	private String code;

	private String message;

	private String customerMessage;

	VerificationCodeErrorCode(String code, String message, String customerMessage) {
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
