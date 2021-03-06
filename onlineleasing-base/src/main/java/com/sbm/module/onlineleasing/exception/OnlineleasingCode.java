package com.sbm.module.onlineleasing.exception;

import com.sbm.module.common.exception.IBusinessCode;

public enum OnlineleasingCode implements IBusinessCode {

	/******************** user ********************/
	U0001("U0001", "用户不能绑定多家商户。userCode:{0}", "用户不能绑定多家商户"),
	U0002("U0002", "用户和商户已经绑定。userCode:{0}, merchantCode:{1}", "用户和商户已经绑定"),
	U0003("U0003", "用户不存在。userCode:{0}", "用户不存在"),

	/******************** register ********************/
	R0001("R0001", "统一信用代码和商户名称不匹配。uscc:{0}, name:{1}", "统一信用代码和商户名称不匹配"),
	R0002("R0002", "查询不到商户信息。uscc:{0}, name:{1}", "查询不到商户信息"),

	/******************** merchant ********************/
	M0001("M0001", "商户已经存在:{0}", "商户已经存在"),
	M0002("M0002", "商户不存在:{0}", "商户不存在"),
	M0003("M0003", "商户写入海鼎失败。", "商户保存失败"),

	/******************** brand ********************/
	B0001("B0001", "品牌已经存在:{0}", "品牌已经存在，请选择已有品牌"),
	B0002("B0002", "品牌和商户已经绑定。merchantCode:{0}, brandCode:{1}", "品牌和商户已经绑定"),
	B0003("B0003", "品牌不存在:{0}", "品牌不存在"),
	B0004("B0004", "品牌写入海鼎失败", "品牌保存失败"),
	B0005("B0005", "品牌业态不存在:{0}", "品牌业态不存在"),

	/******************** shop ********************/
	S0003("S0003", "铺位不存在:{0}", "铺位不存在"),

	/******************** myfavourite ********************/
	F0001("F0001", "用户和铺位已经绑定。userCode:{0}, shopCode:{1}", "用户和铺位已经绑定"),

	/******************** reservation ********************/
	RE0001("RE0001", "至少预约一个铺位", "至少预约一个铺位"),
	RE0002("RE0002", "达到预约次数限制。userCode:{0}, date:{1}, limit:{2}", "您今天预约的次数用完啦");

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
