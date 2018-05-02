package com.sbm.module.onlineleasing.customer.verify.biz;


import com.sbm.module.common.authorization.api.verificationcode.domain.VerificationCodeCheck;

public interface IVerifyService {

	/**
	 * 校验验证码
	 *
	 * @param check
	 * @param keyword
	 */
	void check(VerificationCodeCheck check, String keyword);

	/**
	 * 验证邮件
	 *
	 * @param mail
	 * @return
	 */
	String mail(String mail);

	/**
	 * 验证短信
	 *
	 * @param mobile
	 * @return
	 */
	String sms(String mobile);
}
