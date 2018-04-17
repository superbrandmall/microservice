package com.sbm.module.onlineleasing.customer.verify.biz;


import com.sbm.module.common.authorization.api.verificationcode.domain.VerificationCodeCheck;

public interface IVerifyService {

	/**
	 * 校验验证码
	 *
	 * @param check
	 */
	void check(VerificationCodeCheck check);

	/**
	 * 验证邮件
	 *
	 * @param mail
	 * @return
	 */
	String mail(String mail);


}
