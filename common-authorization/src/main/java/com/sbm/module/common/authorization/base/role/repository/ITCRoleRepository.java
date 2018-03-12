package com.sbm.module.common.authorization.base.role.repository;

import com.sbm.module.common.authorization.base.role.domain.TCRole;
import com.sbm.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "role")
public interface ITCRoleRepository extends IDataRepository<TCRole, Integer> {

	TCRole findOneByCode(String code);

}
