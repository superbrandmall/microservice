package com.sbm.module.common.authorization.api.permission.biz;


import com.sbm.module.common.authorization.api.permission.domain.Permission;

public interface IPermissionService {

	Permission valid(Permission vo);

}
