package com.sbm.module.common.domain;

public class JsonContainer {

	/**
	 * 代码
	 */
	private String code;

	/**
	 * 消息
	 */
	private String message;

	/**
	 * 数据
	 */
	private Object data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
