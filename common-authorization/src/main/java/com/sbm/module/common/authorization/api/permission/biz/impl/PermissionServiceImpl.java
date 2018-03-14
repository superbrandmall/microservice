package com.sbm.module.common.authorization.api.permission.biz.impl;

import com.sbm.module.common.authorization.api.jsonwebtoken.biz.IJSONWebTokenService;
import com.sbm.module.common.authorization.api.permission.biz.IPermissionService;
import com.sbm.module.common.authorization.api.permission.domain.Permission;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends CommonServiceImpl implements IPermissionService {

	@Autowired
	private IJSONWebTokenService jsonWebTokenService;

	@Override
	public void valid(Permission vo) {
		// TODO 判断是否记录该资源，目前没有记录的资源全部放过
		// 判断资源是否需要校验
		// 校验token
		jsonWebTokenService.valid(vo.getLogin(), vo.getToken());


	}
}
