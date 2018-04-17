package com.sbm.module.common.authorization.api.passport.biz;


public interface IPassportCheckService {


	/********** 检查（不存在） **********/

	/**
	 * 手机号不存在
	 *
	 * @param mobile
	 */
	void isNotExistMobile(String mobile);

	/**
	 * 邮箱不存在
	 *
	 * @param email
	 */
	void isNotExistEmail(String email);

	/**
	 * 证件不存在
	 *
	 * @param idCard
	 */
	void isNotExistIdCard(String idCard);

	/********** 检查（存在） **********/

	/**
	 * 手机号存在
	 *
	 * @param mobile
	 */
	void existMobile(String mobile);

	/**
	 * 邮箱存在
	 *
	 * @param email
	 */
	void existEmail(String email);

	/**
	 * 证件存在
	 *
	 * @param idCard
	 */
	void existIdCard(String idCard);

	/**
	 * 用户编号存在
	 *
	 * @param code
	 */
	void existCode(String code);

}
