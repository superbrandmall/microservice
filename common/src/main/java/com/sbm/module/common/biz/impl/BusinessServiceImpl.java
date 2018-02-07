package com.sbm.module.common.biz.impl;

import com.sbm.module.common.biz.IMapService;

import java.util.List;

public abstract class BusinessServiceImpl<T, K> extends CommonServiceImpl<T, K> implements IMapService<T, K> {

	/**
	 * 创建目标实例
	 *
	 * @param e
	 * @return
	 */
	public abstract T newInstance(K e);

	public List<T> findAll(List<K> list) {
		return map(list, this);
	}

}
