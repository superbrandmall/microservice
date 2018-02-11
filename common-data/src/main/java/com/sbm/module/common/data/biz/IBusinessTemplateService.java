package com.sbm.module.common.data.biz;

import com.sbm.module.common.biz.IBusinessService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface IBusinessTemplateService<T, K, ID extends Serializable> extends IBusinessService<T, K> {

	/****************************************************************************************************************/
	// po

	/**
	 * 查询全部po结果（分页）
	 *
	 * @param pageable
	 * @return
	 */
	Page<K> findAllPoByPageable(Pageable pageable);

	/**
	 * 查询全部po结果
	 *
	 * @return
	 */
	List<K> findAllPo();

	/****************************************************************************************************************/
	// vo

	/**
	 * 查询全部结果
	 *
	 * @return
	 */
	List<T> findAll();
}
