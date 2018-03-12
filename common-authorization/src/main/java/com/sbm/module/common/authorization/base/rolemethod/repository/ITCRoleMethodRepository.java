package com.sbm.module.common.authorization.base.rolemethod.repository;

import com.sbm.module.common.authorization.base.rolemethod.domain.TCRoleMethod;
import com.sbm.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "rolemethod")
public interface ITCRoleMethodRepository extends IDataRepository<TCRoleMethod, Integer> {

	TCRoleMethod findAllByRoleCode(String roleCode);

	TCRoleMethod findAllByMethodCode(String methodCode);

}
