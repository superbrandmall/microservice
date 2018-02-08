package com.sbm.module.onlineleasing.data.biz;

import com.sbm.module.common.data.biz.IJpaService;

import java.io.Serializable;

public interface IOLDataService<T, ID extends Serializable> extends IJpaService<T, ID> {

	T findOneByCode(String code);

}