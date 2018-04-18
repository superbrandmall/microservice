package com.sbm.module.onlineleasing.init.authorization.biz.impl;

import com.sbm.module.common.authorization.api.method.client.IMethodClient;
import com.sbm.module.common.authorization.api.role.client.IRoleClient;
import com.sbm.module.common.authorization.api.role.domain.Role;
import com.sbm.module.common.authorization.api.rolemethod.client.IRoleMethodClient;
import com.sbm.module.common.authorization.api.rolemethod.domain.RoleMethod;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
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
		// 创建admin
		initRole("admin", "管理员", "管理员");
		// 创建customer
		initRole("customer", "用户", "用户");
		// 创建interactive
		initRole("interactive", "interactive调用权限", "interactive调用权限");
	}

	private void initRole(String role, String roleName, String remark) {
		if (null == checkJsonContainer(roleClient.findOneByRole(role)))
			roleClient.save(new Role(null, role, roleName, remark));
	}

	/******************** 初始化角色资源关系 ********************/
	@Override
	public void initRoleMethod() {
		// admin权限暂无
		// 关联customer和onlineleasing-customer资源
		initRoleMethod("customer", "onlineleasing-customer");
		// 关联interactive和onlineleasing-interactive资源
		initRoleMethod("interactive", "onlineleasing-interactive");
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
