package com.sbm.module.common.authorization.api.rolemethod.biz;


import com.sbm.module.common.authorization.api.rolemethod.domain.RoleMethod;
import com.sbm.module.common.authorization.base.rolemethod.domain.TCRoleMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface IRoleMethodService {

	void save(List<RoleMethod> vos);

	Page<RoleMethod> findAll(Pageable pageable);

	RoleMethod findOneByRoleCodeAndMethodCode(String roleCode, String methodCode);

	List<RoleMethod> findAllByRoleCodeInAndMethodCode(Collection<String> roleCodes, String methodCode);

}
