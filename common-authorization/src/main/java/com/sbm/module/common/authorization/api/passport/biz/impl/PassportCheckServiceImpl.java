package com.sbm.module.common.authorization.api.passport.biz.impl;

import com.sbm.module.common.authorization.api.passport.biz.IPassportCheckService;
import com.sbm.module.common.authorization.api.user.biz.IUserCheckService;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassportCheckServiceImpl extends CommonServiceImpl implements IPassportCheckService {

	@Autowired
	private IUserCheckService service;

	/********** 检查（不存在） **********/

	@Override
	public void isNotExistMobile(String mobile) {
		service.isNotExistMobile(mobile);
	}

	@Override
	public void isNotExistEmail(String email) {
		service.isNotExistEmail(email);
	}

	@Override
	public void isNotExistIdCard(String idCard) {
		service.isNotExistIdCard(idCard);
	}

	/********** 检查（存在） **********/

	@Override
	public void existMobile(String mobile) {
		service.existMobile(mobile);
	}

	@Override
	public void existEmail(String email) {
		service.existEmail(email);
	}

	@Override
	public void existIdCard(String idCard) {
		service.existIdCard(idCard);
	}

	@Override
	public void existCode(String code) {
		service.existCode(code);
	}

	@Override
	public void existUserRole(String userCode, String role) {
		service.existUserRole(userCode, role);
	}
}
