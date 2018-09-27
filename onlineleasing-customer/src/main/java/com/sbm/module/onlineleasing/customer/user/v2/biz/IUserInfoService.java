package com.sbm.module.onlineleasing.customer.user.v2.biz;

import com.sbm.module.onlineleasing.domain.user.UserMerchant;

public interface IUserInfoService {

	/**
	 * 保存用户商户关系
	 *
	 * @param userCode
	 * @param merchantCode
	 */
	void saveUserMerchant(String userCode, String merchantCode);


	/**
	 * 获取用户商户关系
	 *
	 * @param userCode
	 * @return
	 */
	UserMerchant getUserMerchant(String userCode);

	/**
	 * 保存用户商户
	 *
	 * @param vo
	 */
	void saveUserMerchant(UserMerchant vo);
}
