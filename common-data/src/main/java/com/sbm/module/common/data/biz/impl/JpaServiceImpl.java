package com.sbm.module.common.data.biz.impl;

import com.sbm.module.common.data.biz.IJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

public class JpaServiceImpl<T, ID extends Serializable> implements IJpaService<T, ID> {

	@Autowired
	private PagingAndSortingRepository<T, ID> pagingAndSortingRepository;

	@Override
	public Iterable<T> findAll() {
		return pagingAndSortingRepository.findAll();
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return pagingAndSortingRepository.findAll(pageable);
	}
}
