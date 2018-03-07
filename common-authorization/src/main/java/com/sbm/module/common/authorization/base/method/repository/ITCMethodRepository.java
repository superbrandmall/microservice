package com.sbm.module.common.authorization.base.method.repository;

import com.sbm.module.common.authorization.base.method.domain.TCMethod;
import com.sbm.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "method")
public interface ITCMethodRepository extends IDataRepository<TCMethod, Integer> {

	TCMethod findOneByApplicationNameAndMethodAndAndPattern(String applicationName, String method, String pattern);

}
