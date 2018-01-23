package com.sbm.module.common.data.biz.impl;

import com.sbm.module.common.data.biz.IDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

public class DataServiceImpl<T, ID extends Serializable> implements IDataService<T, ID> {

	@Autowired
	private PagingAndSortingRepository<T, ID> pagingAndSortingRepository;

	@Override
	public Iterable<T> findAll() {
		return pagingAndSortingRepository.findAll();
	}
}
