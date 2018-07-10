package com.sbm.module.common.data.builder.specification;

import lombok.Data;

@Data
public class SpecificationTemplate {

	/**
	 * 字段
	 */
	private String field;

	/**
	 * 值
	 */
	private Object value;

	/**
	 * 操作
	 */
	private SpecificationOperate operate;

	/**
	 * 连接方式
	 */
	private SpecificationJoin join;

	public SpecificationTemplate(String field, Object value, SpecificationOperate operate, SpecificationJoin join) {
		this.field = field;
		this.value = value;
		this.operate = operate;
		this.join = join;
	}

	public SpecificationTemplate() {

	}
}
