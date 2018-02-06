package com.sbm.module.common.biz;

import java.util.List;

public interface IBusinessService<T, K> {

	/**
	 * 查询全部结果
	 * @param list
	 * @return
	 */
	List<T> findAll(List<K> list);
}
