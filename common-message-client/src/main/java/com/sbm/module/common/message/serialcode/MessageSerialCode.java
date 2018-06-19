package com.sbm.module.common.message.serialcode;

import com.sbm.module.common.authorization.api.serialcode.constant.ISerialCode;

public enum MessageSerialCode implements ISerialCode {

	/******************** 权限 ********************/
	CMAILDETAIL("CMAILDETAIL", "邮件发送明细"),
	CSMSDETAIL("CSMSDETAIL", "短信发送明细"),
	CTEMP("CTEMP", "邮件模板");


	private String serialGroup;

	private String remark;

	MessageSerialCode(String serialGroup, String remark) {
		this.serialGroup = serialGroup;
		this.remark = remark;
	}

	@Override
	public String getSerialGroup() {
		return serialGroup;
	}

	@Override
	public String getRemark() {
		return remark;
	}
}
