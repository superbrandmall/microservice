package com.sbm.module.common.data.biz;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

public interface IJpaService<T, ID extends Serializable> {

	Iterable<T> findAll();

	Page<T> findAll(Pageable pageable);

}
