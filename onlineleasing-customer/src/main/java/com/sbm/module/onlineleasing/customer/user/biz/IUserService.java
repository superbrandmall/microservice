package com.sbm.module.onlineleasing.customer.user.biz;

import com.sbm.module.common.authorization.api.passport.domain.Register;
import com.sbm.module.common.authorization.api.role.domain.Role;
import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.common.authorization.api.userrole.domain.UserRole;
import com.sbm.module.onlineleasing.domain.user.UserMerchant;

import java.util.List;

public interface IUserService {

	/**
	 * 登录
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	User login(String username, String password);

	/**
	 * 更新最后登录时间
	 *
	 * @param userCode
	 */
	void updateLastLogin(String userCode);

	/**
	 * 注册
	 *
	 * @param register
	 * @return
	 */
	User register(Register register);

	/**
	 * 校验用户编号存在
	 *
	 * @param userCode
	 */
	void existCode(String userCode);


	User findUserByUserCode(String userCode);

	/**
	 * 保存用户商户关系
	 *
	 * @param userCode
	 * @param merchantCode
	 */
	void saveUserMerchant(String userCode, String merchantCode);

	/**
	 * 更新用户姓名和证件信息
	 *
	 * @param userCode
	 * @param userName
	 * @param idCard
	 * @param idCardType
	 */
	void updateNameAndIdCard(String userCode, String userName, String idCard, Integer idCardType);

	/**
	 * 获取用户商户关系
	 *
	 * @param userCode
	 * @return
	 */
	UserMerchant getUserMerchant(String userCode);

	Role findRoleByRole(String role);

	/**
	 * 绑定用户角色
	 *
	 * @param userCode
	 * @param roleCode
	 */
	void saveUserRole(String userCode, String roleCode);

	/**
	 * 绑定用户角色
	 *
	 * @param vos
	 */
	void saveUserRole(List<UserRole> vos);


}
