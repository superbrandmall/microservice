package com.sbm.module.common.domain;

import lombok.Data;

@Data
public class JsonContainer<T> {

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
	private T data;

}
