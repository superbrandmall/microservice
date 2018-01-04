package com.sbm.module.common.data.biz;

import java.io.Serializable;

public interface IDataService<T, ID extends Serializable> {

	Iterable<T> findAll();

}
