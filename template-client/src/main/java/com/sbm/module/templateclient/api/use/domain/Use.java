package com.sbm.module.templateclient.api.use.domain;

import lombok.Data;

@Data
public class Use {

	/**
	 * 模板名称
	 */
	private String name;

	/**
	 * 模型
	 */
	private Object model;

	/**
	 * 结果
	 */
	private String result;

}
