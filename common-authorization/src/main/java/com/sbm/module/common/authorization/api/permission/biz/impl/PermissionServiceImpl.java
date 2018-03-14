package com.sbm.module.common.authorization.api.permission.biz.impl;

import com.sbm.module.common.authorization.api.jsonwebtoken.biz.IJSONWebTokenService;
import com.sbm.module.common.authorization.api.method.biz.IMethodService;
import com.sbm.module.common.authorization.api.method.constant.MethodConstant;
import com.sbm.module.common.authorization.api.method.domain.Method;
import com.sbm.module.common.authorization.api.permission.biz.IPermissionService;
import com.sbm.module.common.authorization.api.permission.domain.Permission;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
public class PermissionServiceImpl extends CommonServiceImpl implements IPermissionService {

	@Autowired
	private IMethodService methodService;
	@Autowired
	private IJSONWebTokenService jsonWebTokenService;

	@Override
	public void valid(Permission vo) {
		// TODO 判断是否记录该资源，目前没有记录的资源全部放过
		Method method = methodService.findOneByPathAndMethod(vo.getPath(), vo.getMethod());
		if (null != method) {
			// 判断资源是否需要校验
			if (MethodConstant.VALID_FLAG_1.equals(method.getValidFlag())) {
				// 校验token
				jsonWebTokenService.valid(vo.getLogin(), vo.getToken());

			}
		}
	}

}
