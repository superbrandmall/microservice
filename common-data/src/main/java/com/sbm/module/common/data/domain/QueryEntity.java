package com.sbm.module.common.data.domain;

import lombok.Data;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

@Data
public abstract class QueryEntity<T> {

	protected abstract <S extends T> S po();

	protected abstract ExampleMatcher matcher();

	public <S extends T> Example<S> example() {
		return example(po(), matcher());
	}

	public <S extends T> Example<S> example(S po, ExampleMatcher matcher) {
		return Example.of(po, matcher);
	}

}
