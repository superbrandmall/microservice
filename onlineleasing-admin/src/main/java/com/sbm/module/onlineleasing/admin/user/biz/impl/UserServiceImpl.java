package com.sbm.module.onlineleasing.admin.user.biz.impl;

import com.sbm.module.common.authorization.api.passport.client.IPassportClient;
import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.admin.user.biz.IUserService;
import com.sbm.module.onlineleasing.base.usersimple.biz.ITOLUserSimpleService;
import com.sbm.module.onlineleasing.domain.user.UserSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends CommonServiceImpl implements IUserService {

	@Autowired
	private IPassportClient passportClient;

	@Autowired
	private ITOLUserSimpleService userSimpleService;

	@Override
	public User findUserByUserCode(String userCode) {
		return checkJsonContainer(passportClient.findOneByCode(userCode));
	}

	@Override
	public UserSimple findOneByCodeSimple(String code) {
		return mapOneIfNotNull(findUserByUserCode(code),
				u -> mapOneIfNotNull(userSimpleService.findOneByCode(code),
						e -> new UserSimple(u.getCode(), u.getEmail(), u.getMobile(), /**密码不需要*/null, u.getLastLogin(), u.getEmailVerified(), u.getMobileVerified(), u.getSettings(),
								e.getMerchantName(), e.getBrandName(), e.getModality(), e.getWebsite(), e.getFile())));
	}
}
