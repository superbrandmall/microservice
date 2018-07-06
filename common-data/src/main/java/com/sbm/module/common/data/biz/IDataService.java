package com.sbm.module.common.data.biz;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface IDataService<T, ID extends Serializable> {

	T findOne(ID id);

	List<T> findAll();

	Page<T> findAll(Pageable pageable);

	<S extends T> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends T> S save(S po);

	void delete(T po);

	<S extends T> List<S> save(Iterable<S> pos);

	<S extends T> List<S> saveOrDelete(Iterable<S> pos);

	<S extends T> S updateState(S po, String operate);

}
