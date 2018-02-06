package com.sbm.module.common.data.biz.impl;

import com.sbm.module.common.data.biz.IJpaService;
import com.sbm.module.common.data.dao.IDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class JpaServiceImpl<T, ID extends Serializable> implements IJpaService<T, ID> {

	@Autowired
	private IDataRepository<T, ID> repository;

	@Override
	public List<T> findAll() {
		return repository.findAll();
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}


}
