package com.sbm.module.common.data.biz.impl;

import com.sbm.module.common.biz.IMapService;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.data.biz.IDataService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public abstract class DataServiceImpl<T, K, ID extends Serializable> extends CommonServiceImpl<T, K> implements IDataService<T, K, ID>, IMapService<T, K> {

	@Autowired
	private JpaServiceImpl<K, ID> jpaService;

	public abstract T newInstance(K e);

	public List<T> findAll() {
		return map(jpaService.findAll(), this);
	}

}
