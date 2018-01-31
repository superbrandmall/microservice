package com.sbm.module.common.data.biz;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface IDataService<T, K, ID extends Serializable> {

	/****************************************************************************************************************/
	// po

	Page<K> findAllPo(Pageable pageable);

	/****************************************************************************************************************/
	// vo


	List<T> findAllVo();

}
