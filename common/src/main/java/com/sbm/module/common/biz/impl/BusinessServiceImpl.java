package com.sbm.module.common.biz.impl;

import com.sbm.module.common.biz.IMapService;

import java.util.List;

public abstract class BusinessServiceImpl<T, K> extends CommonServiceImpl implements IMapService<T, K> {

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
		return super.map(result, e -> service.newInstance(e));
	}

	/**
	 * 创建目标实例
	 *
	 * @param e
	 * @return
	 */
	public abstract T newInstance(K e);

	public List<T> findAll(List<K> result) {
		return map(result, this);
	}

}
