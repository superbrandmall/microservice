package com.sbm.module.common.data.biz.impl;

import com.sbm.module.common.data.biz.IJpaService;
import com.sbm.module.common.data.dao.IDataRepository;
import com.sbm.module.common.data.domain.DataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JpaServiceImpl<T, ID extends Serializable> implements IJpaService<T, ID> {

	@Autowired
	protected IDataRepository<T, ID> repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<T> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<T> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	@Transactional
	public <S extends T> S save(S po) {
		if (po instanceof DataEntity) {
			((DataEntity) po).setCreated(new Date());
			((DataEntity) po).setUpdated(new Date());
			// 默认启用
			if (null == ((DataEntity) po).getState()) {
				((DataEntity) po).setState(1);
			}
		}
		return repository.save(po);
	}

	@Override
	@Transactional
	public <S extends T> List<S> save(Iterable<S> pos) {
		List<S> list = new ArrayList<>();
		if (null == pos) {
			return list;
		}
		for (S po : pos) {
			list.add(save(po));
		}
		return list;
	}
}
