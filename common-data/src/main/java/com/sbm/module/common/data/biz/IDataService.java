package com.sbm.module.common.data.biz;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface IDataService<T, K, ID extends Serializable> {

	/****************************************************************************************************************/
	// po

	/**
	 * 查询全部po结果（分页）
	 * @param pageable
	 * @return
	 */
	Page<K> findAllPoByPageable(Pageable pageable);

	/****************************************************************************************************************/
	// vo

	/**
	 * 查询全部vo结果
	 * @return
	 */
	List<T> findAllVo();

}
