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
	 * @param iter
	 * @return
	 */
	protected List<K> newArrayList(Iterable<K> iter) {
		return Lists.newArrayList(iter);
	}

	/**
	 * K类型iterator转T类型list
	 *
	 * @param iter
	 * @param service
	 * @return
	 */
	protected List<T> map(Iterable<K> iter, IMapService<T, K> service) {
		return map(newArrayList(iter), service);
	}

	/**
	 * K类型list转T类型list
	 *
	 * @param list
	 * @param service
	 * @return
	 */
	protected List<T> map(List<K> list, IMapService<T, K> service) {
		return list.stream().map(e -> service.newInstance(e)).collect(Collectors.toList());
	}

}
