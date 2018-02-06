package com.sbm.module.onlineleasing.base.mall.repository;

import com.sbm.module.common.data.dao.IDataRepository;
import com.sbm.module.onlineleasing.base.mall.domain.TOLMall;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "mall")
public interface ITOLMallRepository extends IDataRepository<TOLMall, Integer> {


}
