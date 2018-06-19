package com.sbm.module.common.authorization.serialcode;

import com.sbm.module.common.authorization.api.serialcode.constant.ISerialCode;

public enum AuthorizationSerialCode implements ISerialCode {

	/******************** 权限 ********************/
	CUSER("CUSER", "用户"),
	CROLE("CROLE", "角色"),
	CMETHOD("CMETHOD", "方法"),
	CMAILDETAIL("CMAILDETAIL", "邮件发送明细"),
	CSMSDETAIL("CSMSDETAIL", "短信发送明细"),
	CBUSINESSCODE("CBUSINESSCODE", "业务代码"),
	CTEMP("CTEMP", "邮件模板");


	private String serialGroup;

	private String remark;

	AuthorizationSerialCode(String serialGroup, String remark) {
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
