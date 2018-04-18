package com.sbm.module.common.authorization.api.role.biz;


import com.sbm.module.common.authorization.api.role.domain.Role;
import com.sbm.module.common.authorization.base.role.domain.TCRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRoleService {

	void save(Role vo);

	Page<Role> findAll(Pageable pageable);

	Role findOneByRole(String role);

}
