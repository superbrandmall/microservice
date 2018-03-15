package com.sbm.module.common.authorization.api.rolemethod.biz;


import com.sbm.module.common.authorization.api.rolemethod.domain.RoleMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRoleMethodService {

	void save(List<RoleMethod> vos);

	Page<RoleMethod> findAll(Pageable pageable);

	RoleMethod findOneByRoleCodeAndMethodCode(String roleCode, String methodCode);

}
