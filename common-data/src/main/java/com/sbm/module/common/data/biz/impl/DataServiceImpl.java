package com.sbm.module.common.data.biz.impl;

import com.sbm.module.common.biz.impl.BusinessServiceImpl;
import com.sbm.module.common.data.biz.IDataService;
import com.sbm.module.common.data.dao.IDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * @param <T>  目标类型
 * @param <K>  当前类型
 * @param <ID> 主键
 */
public abstract class DataServiceImpl<T, K, ID extends Serializable> extends BusinessServiceImpl<T, K> implements IDataService<T, K, ID> {

	@Autowired
	private IDataRepository<K, ID> repository;

	/****************************************************************************************************************/
	// po
	@Override
	public Page<K> findAllPoByPageable(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public List<K> findAllPo() {
		return repository.findAll();
	}

	/****************************************************************************************************************/
	// vo

	@Override
	public List<T> findAll() {
		return findAll(findAllPo());
	}

}
