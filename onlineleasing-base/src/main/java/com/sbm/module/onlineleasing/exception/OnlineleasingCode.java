package com.sbm.module.onlineleasing.exception;

import com.sbm.module.common.exception.IBusinessCode;

public enum OnlineleasingCode implements IBusinessCode {

	/******************** brand ********************/
	B0001("B0001", "品牌已经存在:{0}", "品牌已经存在"),
	B0002("B0002", "品牌和商户已经绑定。merchantCode:{0}, brandCode:{1}", "品牌和商户已经绑定"),

	/******************** myfavourite ********************/
	F0001("F0001", "用户和铺位已经绑定。userCode:{0}, shopCode:{1}", "用户和铺位已经绑定");

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
