package com.sbm.module.onlineleasing.init.authorization.biz;

public interface IAuthorizationService {

	/**
	 * 初始化角色
	 */
	void initRole();

	/**
	 * 初始化角色资源关系
	 */
	void initRoleMethod();

	/**
	 * 初始化用户
	 */
	void initUser();

	/**
	 * 初始化用户角色关系
	 */
	void initUserRole();
}
