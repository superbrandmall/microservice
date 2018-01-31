package com.sbm.module.common.data.biz;

import java.io.Serializable;
import java.util.List;

public interface IDataService<T, K, ID extends Serializable> {


	<T, K> List<T> findAll();

}
