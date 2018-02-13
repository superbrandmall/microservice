package com.sbm.module.onlineleasing.data.biz.impl;

import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import com.sbm.module.onlineleasing.data.biz.IOLDataService;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public class OLDataServiceImpl<T, ID extends Serializable> extends DataServiceImpl<T, ID> implements IOLDataService<T, ID> {

	@Autowired
	protected IOLDataRepository<T, ID> repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public T findOneByCode(String code) {
		return repository.findOneByCode(code);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<T> findAllByCode(String code) {
		return repository.findAllByCode(code);
	}
}
