package com.sbm.module.common.authorization.base.userrole.repository;

import com.sbm.module.common.authorization.base.userrole.domain.TCUserRole;
import com.sbm.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "userrole")
public interface ITCUserRoleRepository extends IDataRepository<TCUserRole, Integer> {

	List<TCUserRole> findAllByUserCode(String userCode);

	List<TCUserRole> findAllByRoleCode(String roleCode);

	TCUserRole findOneByUserCodeAndRoleCode(String userCode, String roleCode);

}
