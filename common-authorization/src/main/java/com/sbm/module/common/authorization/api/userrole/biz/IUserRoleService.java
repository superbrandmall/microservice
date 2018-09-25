package com.sbm.module.common.authorization.api.userrole.biz;


import com.sbm.module.common.authorization.api.userrole.domain.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserRoleService {

	void save(List<UserRole> vos);

	Page<UserRole> findAll(Pageable pageable);

	List<UserRole> findAllByUserCode(String userCode);

	UserRole findOneByUserCodeAndRoleCode(String userCode, String roleCode);

	void deleteByUserCode(String userCode);
}
