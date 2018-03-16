package com.sbm.module.common.authorization.api.passport.biz.impl;

import com.sbm.module.common.authorization.api.passport.biz.IPassportService;
import com.sbm.module.common.authorization.api.user.biz.IUserService;
import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.common.authorization.exception.AuthorizationCode;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.exception.BusinessException;
import com.sbm.module.common.util.CodecUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PassportServiceImpl extends CommonServiceImpl implements IPassportService {

	@Autowired
	private IUserService service;

	@Override
	public User login(String username, String password) {
		User po = service.findOneByUsername(username);
		// 用户名错误
		if (null == po) {
			throw new BusinessException(AuthorizationCode.PP0001);
		}
		// 密码错误
		if (!CodecUtil.sha1Hex(password).equals(po.getPassword())) {
			throw new BusinessException(AuthorizationCode.PP0002);
		}
		return po;
	}

	@Override
	@Transactional
	public void updateLastLogin(String code) {
		service.updateLastLogin(code);
	}
}
