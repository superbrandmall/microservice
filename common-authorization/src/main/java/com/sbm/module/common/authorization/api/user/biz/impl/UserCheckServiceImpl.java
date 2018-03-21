package com.sbm.module.common.authorization.api.user.biz.impl;

import com.sbm.module.common.authorization.api.user.biz.IUserCheckService;
import com.sbm.module.common.authorization.base.user.biz.ITCUserService;
import com.sbm.module.common.authorization.base.user.domain.TCUser;
import com.sbm.module.common.authorization.base.usersettings.biz.ITCUserSettingsService;
import com.sbm.module.common.authorization.base.usersettings.domain.TCUserSettings;
import com.sbm.module.common.authorization.exception.AuthorizationCode;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCheckServiceImpl extends CommonServiceImpl implements IUserCheckService {

	@Autowired
	private ITCUserService service;
	@Autowired
	private ITCUserSettingsService userSettingsService;

	/********** 检查（不存在） **********/

	@Override
	public void isNotExistMobile(String mobile) {
		TCUser po = service.findOneByMobile(mobile);
		if (null != po) {
			throw new BusinessException(AuthorizationCode.PP0003, new Object[]{mobile});
		}
	}

	@Override
	public void isNotExistEmail(String email) {
		TCUser po = service.findOneByEmail(email);
		if (null != po) {
			throw new BusinessException(AuthorizationCode.PP0004, new Object[]{email});
		}
	}

	@Override
	public void isNotExistIdCard(String idCard) {
		TCUserSettings po = userSettingsService.findOneByIdCard(idCard);
		if (null != po) {
			throw new BusinessException(AuthorizationCode.PP0005, new Object[]{idCard});
		}
	}

	/********** 检查（存在） **********/

	@Override
	public void existMobile(String mobile) {
		TCUser po = service.findOneByMobile(mobile);
		if (null == po) {
			throw new BusinessException(AuthorizationCode.PP0006, new Object[]{mobile});
		}
	}

	@Override
	public void existEmail(String email) {
		TCUser po = service.findOneByEmail(email);
		if (null == po) {
			throw new BusinessException(AuthorizationCode.PP0007, new Object[]{email});
		}
	}

	@Override
	public void existIdCard(String idCard) {
		TCUserSettings po = userSettingsService.findOneByIdCard(idCard);
		if (null == po) {
			throw new BusinessException(AuthorizationCode.PP0008, new Object[]{idCard});
		}
	}
}
