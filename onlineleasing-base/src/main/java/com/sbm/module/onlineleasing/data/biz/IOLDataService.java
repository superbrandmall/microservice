package com.sbm.module.onlineleasing.data.biz;

import com.sbm.module.common.data.biz.IDataService;

import java.io.Serializable;

public interface IOLDataService<T, ID extends Serializable> extends IDataService<T, ID> {

	T findOneByCode(String code);

}
