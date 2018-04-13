package com.sbm.module.common.authorization.api.verificationcode.biz;


import com.sbm.module.common.authorization.api.verificationcode.domain.VerificationCode;
import com.sbm.module.common.authorization.api.verificationcode.domain.VerificationCodeCheck;
import com.sbm.module.common.authorization.api.verificationcode.domain.VerificationCodeSetting;

public interface IVerificationCodeService {

	/**
	 * 生成验证码
	 *
	 * @param setting
	 * @return
	 */
	VerificationCode generate(VerificationCodeSetting setting);

	/**
	 * 校验验证码
	 *
	 * @param check
	 */
	void check(VerificationCodeCheck check);

}
