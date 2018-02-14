package com.sbm.module.common.biz.impl;

import com.google.common.collect.Lists;
import com.sbm.module.common.biz.IMapService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @param <T> 目标类型
 * @param <K> 当前类型
 */
public class CommonServiceImpl<T, K> {

	/**
	 * iterator转list
	 *
	 * @param result
	 * @return
	 */
	protected List<K> newArrayList(Iterable<K> result) {
		return Lists.newArrayList(result);
	}

	/**
	 * K类型iterator转T类型list
	 *
	 * @param result
	 * @param service
	 * @return
	 */
	protected List<T> map(Iterable<K> result, IMapService<T, K> service) {
		return map(newArrayList(result), service);
	}

	/**
	 * K类型list转T类型list
	 *
	 * @param result
	 * @param service
	 * @return
	 */
	protected List<T> map(List<K> result, IMapService<T, K> service) {
		return result.stream().map(e -> service.newInstance(e)).collect(Collectors.toList());
	}

}
