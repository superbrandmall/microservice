package com.sbm.module.onlineleasing.init.authorization.biz.impl;

import com.sbm.module.common.authorization.api.method.client.IMethodClient;
import com.sbm.module.common.authorization.api.role.client.IRoleClient;
import com.sbm.module.common.authorization.api.role.domain.Role;
import com.sbm.module.common.authorization.api.rolemethod.client.IRoleMethodClient;
import com.sbm.module.common.authorization.api.rolemethod.domain.RoleMethod;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.init.RoleEnum;
import com.sbm.module.onlineleasing.init.RoleMethodEnum;
import com.sbm.module.onlineleasing.init.authorization.biz.IAuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AuthorizationServiceImpl extends CommonServiceImpl implements IAuthorizationService {

	@Autowired
	private IRoleClient roleClient;
	@Autowired
	private IMethodClient methodClient;
	@Autowired
	private IRoleMethodClient roleMethodClient;

	/******************** 初始化角色 ********************/
	@Override
	public void initRole() {
		for (RoleEnum e : RoleEnum.values()) {
			initRole(e.getRole());
		}
	}

	private void initRole(Role role) {
		if (null == checkJsonContainer(roleClient.findOneByRole(role.getRole())))
			roleClient.save(role);
	}

	/******************** 初始化角色资源关系 ********************/
	@Override
	public void initRoleMethod() {
		// admin权限暂无

		for (RoleMethodEnum e : RoleMethodEnum.values()) {
			initRoleMethod(e.getRole(), e.getApplicationName());
		}
	}

	private void initRoleMethod(String role, String applicationName) {
		List<RoleMethod> roleMethods = new ArrayList<>();
		// 获取roleCode
		String roleCode = checkJsonContainer(roleClient.findOneByRole(role)).getCode();
		// 获取methodCode列表
		checkJsonContainer(methodClient.findAllByApplicationName(applicationName)).forEach(e -> {
			// 判断是否存在
			if (null == checkJsonContainer(roleMethodClient.findOneByRoleCodeAndMethodCode(roleCode, e.getCode())))
				roleMethods.add(new RoleMethod(roleCode, e.getCode()));
		});
		// 插入
		roleMethodClient.save(roleMethods);
	}
}
