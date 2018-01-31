package com.sbm.module.common.data.biz;

import java.io.Serializable;

public interface IJpaService<T, ID extends Serializable> {

	Iterable<T> findAll();

}
