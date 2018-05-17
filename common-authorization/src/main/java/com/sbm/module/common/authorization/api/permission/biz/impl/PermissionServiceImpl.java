package com.sbm.module.common.authorization.api.permission.biz.impl;

import com.sbm.module.common.authorization.api.frequency.biz.IFrequencyService;
import com.sbm.module.common.authorization.api.frequency.domain.Frequency;
import com.sbm.module.common.authorization.api.jsonwebtoken.biz.IJSONWebTokenService;
import com.sbm.module.common.authorization.api.jsonwebtoken.domain.JSONWebToken;
import com.sbm.module.common.authorization.api.method.biz.IMethodService;
import com.sbm.module.common.authorization.api.method.constant.MethodConstant;
import com.sbm.module.common.authorization.api.method.domain.Method;
import com.sbm.module.common.authorization.api.permission.biz.IPermissionService;
import com.sbm.module.common.authorization.api.permission.domain.Permission;
import com.sbm.module.common.authorization.api.rolemethod.biz.IRoleMethodService;
import com.sbm.module.common.authorization.api.rolemethod.domain.RoleMethod;
import com.sbm.module.common.authorization.api.userrole.biz.IUserRoleService;
import com.sbm.module.common.authorization.exception.AuthorizationCode;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl extends CommonServiceImpl implements IPermissionService {

	@Autowired
	private IMethodService methodService;
	@Autowired
	private IJSONWebTokenService jsonWebTokenService;
	@Autowired
	private IUserRoleService userRoleService;
	@Autowired
	private IRoleMethodService roleMethodService;

	@Autowired
	private IFrequencyService frequencyService;

	@Override
	public Permission valid(Permission vo) {
		// 查询资源
		Method method = methodService.findOneByPathAndMethod(vo.getPath(), vo.getMethod());

		// 检查频率
		frequencyService.checkFrequency(new Frequency(vo.getIp(), vo.getLogin(), method == null ? vo.getPath() : method.getPattern()));

		// TODO 判断是否记录该资源，目前没有记录的资源全部放过
		// 检查权限
		if (null != method) {
			// 判断资源是否需要校验
			if (MethodConstant.VALID_FLAG_1.equals(method.getValidFlag())) {
				// 校验token
				jsonWebTokenService.valid(vo.getLogin(), vo.getToken());
				// 根据全部角色，方法，查询是否有绑定
				List<RoleMethod> roleMethods = roleMethodService.findAllByRoleCodeInAndMethodCode(map(userRoleService.findAllByUserCode(vo.getLogin()), e -> e.getRoleCode()), method.getCode());
				if (roleMethods.isEmpty()) {
					throw new BusinessException(AuthorizationCode.P0001, new Object[]{vo.getLogin(), method.getCode()});
				}
				// 校验通过，刷新token
				vo.setToken(jsonWebTokenService.token(new JSONWebToken(vo.getLogin())));
			}
		}
		return vo;
	}

}
