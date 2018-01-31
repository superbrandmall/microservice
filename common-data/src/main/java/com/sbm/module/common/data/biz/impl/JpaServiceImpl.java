package com.sbm.module.common.data.biz.impl;

import com.sbm.module.common.data.biz.IJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;

public class JpaServiceImpl<T, ID extends Serializable> implements IJpaService<T, ID> {

	@Autowired
	private PagingAndSortingRepository<T, ID> pagingAndSortingRepository;

	@Override
	public Iterable<T> findAll() {
		return pagingAndSortingRepository.findAll();
	}
}
