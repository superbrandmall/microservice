package com.sbm.module.onlineleasing.data.biz.impl;

import com.sbm.module.common.data.biz.impl.JpaServiceImpl;
import com.sbm.module.onlineleasing.data.biz.IOLDataService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

public class OLDataServiceImpl<T, ID extends Serializable> extends JpaServiceImpl<T, ID> implements IOLDataService<T, ID> {

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public T findOneByCode(String code) {
		return super.repository.findOneByCode(code);
	}

}
