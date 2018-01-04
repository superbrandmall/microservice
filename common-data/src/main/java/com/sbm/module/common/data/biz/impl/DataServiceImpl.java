package com.sbm.module.common.data.biz.impl;

import com.sbm.module.common.data.biz.IDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public class DataServiceImpl<T, ID extends Serializable> implements IDataService<T, ID> {

	@Autowired
	private CrudRepository<T, ID> crudRepository;

	@Override
	public Iterable<T> findAll() {
		return crudRepository.findAll();
	}
}
