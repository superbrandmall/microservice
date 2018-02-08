package com.sbm.module.onlineleasing.data.repository;

import com.sbm.module.common.data.repository.IDataRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface IOLDataRepository<T, ID extends Serializable> extends IDataRepository<T, ID> {

	T findOneByCode(String code);

}
