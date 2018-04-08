package com.sbm.module.onlineleasing.interactive.bid.exception;

import com.sbm.module.common.exception.IBusinessCode;

public enum BidCode implements IBusinessCode {

	C11200("C11200", "审批通过的出价过期日期不能为空。billNumber:{0}", "审批通过的出价过期日期不能为空"),
	C11201("C11201", "审批结果参数不正确。billNumber:{0}, isApprove:{1}", "审批结果参数不正确"),
	C11202("C11202", "是否生效参数不正确。billNumber:{0}, isEffect:{1}", "是否生效参数不正确"),
	C11203("C11203", "出价不存在。billNumber:{0}", "出价不存在");


	private String clazz;

	private String code;

	private String message;

	private String customerMessage;

	BidCode(String code, String message, String customerMessage) {
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
