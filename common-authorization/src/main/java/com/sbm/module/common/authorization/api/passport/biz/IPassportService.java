package com.sbm.module.common.authorization.api.passport.biz;


import com.sbm.module.common.authorization.api.passport.domain.ChangePassword;
import com.sbm.module.common.authorization.api.passport.domain.ForgetPassword;
import com.sbm.module.common.authorization.api.passport.domain.Register;
import com.sbm.module.common.authorization.api.user.domain.User;

public interface IPassportService {

	/********** 登录 **********/

	/**
	 * 登录
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	User login(String username, String password);

	/**
	 * 登录简单版
	 *
	 * @param username
	 * @return
	 */
	User loginSimple(String username);

	/**
	 * 更新最后登录时间
	 *
	 * @param code
	 */
	void updateLastLogin(String code);

	/********** 注册 **********/

	/**
	 * 用户注册
	 *
	 * @param register
	 * @return
	 */
	User register(Register register);

	/**
	 * 更新证件信息
	 *
	 * @param code
	 * @param name
	 */
	void updateName(String code, String name);

	/**
	 * 更新证件信息
	 *
	 * @param code
	 * @param name
	 * @param idCard
	 * @param idCardType
	 */
	void updateNameAndIdCard(String code, String name, String idCard, Integer idCardType);

	/**
	 * 忘记密码
	 *
	 * @param vo
	 */
	void forgetPassword(ForgetPassword vo);

	/**
	 * 修改密码
	 *
	 * @param vo
	 */
	void changePassword(ChangePassword vo);

	/**
	 * 通过用户编号查询用户信息
	 *
	 * @param userCode
	 * @return
	 */
	User findOneByCode(String userCode);

	/**
	 * 更新用户信息
	 *
	 * @param vo
	 */
	void updateUser(User vo);

	/**
	 * 通过编号删除
	 *
	 * @param code
	 */
	void deleteByCode(String code);
}
