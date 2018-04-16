package com.sbm.module.partner.hl95.rest.sms.constant;

public enum SMSCode {

	C00("00", "提交成功"),
	C1("1", "参数不完整，请检查所带的参数名是否都正确"),
	C2("2", "鉴权失败，一般是用户名密码不对"),
	C3("3", "号码数量超出50条"),
	C4("4", "发送失败"),
	C5("5", "余额不足"),
	C6("6", "发送内容含屏蔽词"),
	C7("7", "短信内容超出350个字"),
	C72("72", "内容被审核员屏蔽"),
	C8("8:OverLimit!", "号码列表中没有合法的手机号码或手机号为黑名单或验证码类每小时超过限制条数"),
	C9("9", "夜间管理，不允许一次提交超过20个号码"),
	C10("10", "{txt}不应当出现在提交数据中，请修改[模板类账号]（适用于模板类帐户）"),
	C11("11", "模板匹配成功[模板类必审、免审账号]（适用于模板类帐户）"),
	C12("12", "未匹配到合适模板，已提交至审核员审核[模板类必审账号]（适用于模板类帐户）"),
	C13("13", "未匹配到合适模板，无法下发，请修改[模板类免审账号]（适用于模板类帐户）"),
	C14("14", "该账户没有模板[模板类账号]（适用于模板类帐户）"),
	C15("15", "操作失败[模板类账号]（适用于模板类帐户）"),
	C02("02", "手机号码为黑名单"),
	C81("81", "手机号码错误，请检查手机号是否正确"),
	CERRIP("ERR IP", "IP验证未通过，请联系管理员增加鉴权IP"),;

	private String returnCode;

	private String returnMessage;

	SMSCode(String returnCode, String returnMessage) {
		this.returnCode = returnCode;
		this.returnMessage = returnMessage;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

}
