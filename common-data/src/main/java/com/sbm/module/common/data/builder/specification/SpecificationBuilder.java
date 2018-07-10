package com.sbm.module.common.data.builder.specification;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
public class SpecificationBuilder<T> {

	private List<SpecificationTemplate> templates = new ArrayList<>();

	/**
	 * 构建
	 *
	 * @return
	 */
	public Specification<T> build() {
		return new SpecificationWrapper<>(templates);
	}

	/********** add **********/

	/**
	 * @param field
	 * @param value
	 * @param operate
	 * @param join
	 * @param nullable true: 空值加入查询条件, false: 空值不加入查询条件
	 * @return
	 */
	public SpecificationBuilder<T> add(String field, Object value, SpecificationOperate operate, SpecificationJoin join, boolean nullable) {
		if (nullable || null != value) {
			templates.add(new SpecificationTemplate(field, value, operate, join));
		}
		return this;
	}

	public SpecificationBuilder<T> add(String field, Object value, SpecificationOperate operate, SpecificationJoin join) {
		return add(field, value, operate, join, false);
	}

	/********** and **********/

	public SpecificationBuilder<T> and(String field, Object value, SpecificationOperate operate, boolean nullable) {
		return this.add(field, value, operate, SpecificationJoin.AND, nullable);
	}

	public SpecificationBuilder<T> and(String field, Object value, SpecificationOperate operate) {
		return this.and(field, value, operate, false);
	}

	/********** or **********/

	public SpecificationBuilder<T> or(String field, Object value, SpecificationOperate operate, boolean nullable) {
		return this.add(field, value, operate, SpecificationJoin.OR, nullable);
	}

	public SpecificationBuilder<T> or(String field, Object value, SpecificationOperate operate) {
		return this.or(field, value, operate, false);
	}
}
