package com.sbm.module.common.authorization.base.rolemethod.repository;

import com.sbm.module.common.authorization.base.rolemethod.domain.TCRoleMethod;
import com.sbm.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "rolemethod")
public interface ITCRoleMethodRepository extends IDataRepository<TCRoleMethod, Integer> {

	List<TCRoleMethod> findAllByRoleCode(String roleCode);

	List<TCRoleMethod> findAllByMethodCode(String methodCode);

	TCRoleMethod findOneByRoleCodeAndMethodCode(String roleCode, String methodCode);

}
