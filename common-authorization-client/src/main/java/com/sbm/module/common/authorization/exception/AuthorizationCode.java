package com.sbm.module.common.authorization.exception;

import com.sbm.module.common.exception.IBusinessCode;

public enum AuthorizationCode implements IBusinessCode {

	/******************** passport ********************/
	PP0001("PP0001", "用户名错误", "用户名或者密码错误"),
	PP0002("PP0002", "密码错误", "用户名或者密码错误"),
	PP0003("PP0003", "手机号:{0}已存在", "手机号已存在"),
	PP0004("PP0004", "邮箱:{0}已存在", "邮箱已存在"),
	PP0005("PP0005", "证件:{0}已存在", "证件已存在"),
	PP0006("PP0006", "手机号:{0}不存在", "手机号不存在"),
	PP0007("PP0007", "邮箱:{0}不存在", "邮箱不存在"),
	PP0008("PP0008", "证件:{0}不存在", "证件不存在"),

	/******************** permission ********************/
	P0001("P0001", "用户没有访问该资源的权限, userCode: {0}, methodCode: {1}", "用户没有访问该资源的权限"),

	/******************** jwt ********************/
	JWT0001("JWT0001", "token解析异常", "token解析异常"),
	JWT0002("JWT0002", "token过期", "token过期"),
	JWT0003("JWT0003", "login和token不匹配", "login和token不匹配"),

	/******************** 用户异常 ********************/
	UR0001("UR0001", "用户角色已绑定, userCode: {0}, roleCode: {1}", "用户角色已绑定"),
	/******************** 角色异常 ********************/
	RM0001("RM0001", "角色方法已绑定, roleCode: {0}, methodCode: {1}", "角色方法已绑定"),
	/******************** 方法异常 ********************/
	M0001("M0001", "一个@RequestMapping有且只能有一个method, clazz: {0}, method: {1}", "生成api失败"),
	M0002("M0002", "一个@RequestMapping有且只能有一个value, clazz: {0}, method: {1}", "生成api失败");


	private String clazz;

	private String code;

	private String message;

	private String customerMessage;

	AuthorizationCode(String code, String message, String customerMessage) {
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
