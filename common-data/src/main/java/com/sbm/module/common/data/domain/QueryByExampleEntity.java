package com.sbm.module.common.data.domain;

import lombok.Data;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

/**
 * QBE只支持字符串的模糊查询，泛用性一般
 * 推荐使用SpecificationEntity
 *
 * @param <T>
 */
@Deprecated
@Data
public abstract class QueryByExampleEntity<T> {

	protected abstract <S extends T> S po();

	protected abstract ExampleMatcher matcher();

	public <S extends T> Example<S> example() {
		return example(po(), matcher());
	}

	public <S extends T> Example<S> example(S po, ExampleMatcher matcher) {
		return Example.of(po, matcher);
	}

}
