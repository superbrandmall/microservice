package com.sbm.module.common.biz;

import java.util.List;

public interface IBusinessService<T, K> {

	/**
	 * 查询全部结果
	 * @param result
	 * @return
	 */
	List<T> findAll(List<K> result);
}
