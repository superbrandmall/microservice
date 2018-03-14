package com.sbm.module.common.exception;

public enum BusinessCode implements IBusinessCode {

	/********** 标准回复 **********/
	C0("C0", "成功", "成功"),

	/********** 异常回复 **********/
	C9996("C9996", "请求中缺少必要参数", "请求中缺少必要参数"),
	C9997("C9997", "参数不满足条件", "参数不满足条件"),
	C9998("C9998", "缺少必要参数，空指针", "缺少必要参数"),
	C9999("C9999", "失败", "正大君出错啦");

	private String clazz;

	private String code;

	private String message;

	private String customerMessage;

	BusinessCode(String code, String message, String customerMessage) {
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
