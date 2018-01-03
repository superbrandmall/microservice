package com.sbm.module.templateclient.api.use.domain;

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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getModel() {
		return model;
	}

	public void setModel(Object model) {
		this.model = model;
	}
}
