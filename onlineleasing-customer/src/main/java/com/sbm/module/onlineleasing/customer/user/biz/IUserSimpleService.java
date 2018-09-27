package com.sbm.module.onlineleasing.customer.user.biz;

import com.sbm.module.onlineleasing.domain.user.UserSimple;

public interface IUserSimpleService {

	/**
	 * 保存用户简单信息（部分）
	 *
	 * @param userCode
	 * @param merchantName
	 * @param brandName
	 * @param modality
	 * @param website
	 * @param file
	 */
	void saveUserSimple(String userCode, String merchantName, String brandName, String modality, String website, String file);

	/**
	 * 获取用户简单信息
	 *
	 * @param userCode
	 * @return
	 */
	UserSimple getUserSimple(String userCode);

	/**
	 * 保存用户简单信息（完整）
	 *
	 * @param vo
	 */
	void saveUserSimple(UserSimple vo);

}
