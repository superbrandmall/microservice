package com.sbm.module.common.authorization.api.passport.biz.impl;

import com.sbm.module.common.authorization.api.passport.biz.IPassportCheckService;
import com.sbm.module.common.authorization.api.passport.biz.IPassportService;
import com.sbm.module.common.authorization.api.passport.domain.Register;
import com.sbm.module.common.authorization.api.user.biz.IUserService;
import com.sbm.module.common.authorization.api.user.constant.UserConstant;
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
	@Autowired
	private IPassportCheckService passportCheckService;

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

	@Override
	@Transactional
	public User register(Register register) {
		// 创建用户
		User user = new User();
		user.setMobile(register.getMobile());
		user.setEmail(register.getEmail());
		user.setPassword(register.getPassword());
		user.getSettings().setName(register.getName());
		user.getSettings().setLang(register.getLang());
		user.getSettings().setInternational(register.getInternational());
		// 校验手机
		passportCheckService.isNotExistMobile(user.getMobile());
		// 校验邮箱
		passportCheckService.isNotExistEmail(user.getEmail());
		// 如果是境内人士，默认手机号校验成功
		if (UserConstant.INTERNATIONAL_0.equals(user.getSettings().getInternational())) {
			user.setMobileVerified(UserConstant.VERIFIED_1);
		} else {
			user.setEmailVerified(UserConstant.VERIFIED_1);
		}
		service.save(user);
		// TODO 校验验证码
		return user;
	}
}
