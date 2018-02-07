package com.sbm.module.common.data.biz;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface IJpaService<T, ID extends Serializable> {

	List<T> findAll();

	Page<T> findAll(Pageable pageable);

	T findOneByCode(String code);

	<S extends T> S save(S po);

}
