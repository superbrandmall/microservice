package com.sbm.module.onlineleasing.customer.login.biz.impl;

import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.onlineleasing.customer.login.biz.ILoginService;
import com.sbm.module.onlineleasing.customer.user.biz.IUserSimpleService;
import com.sbm.module.onlineleasing.domain.login.LoginResult;
import com.sbm.module.onlineleasing.domain.login.LoginSimple;
import com.sbm.module.onlineleasing.domain.user.UserSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
public class LoginServiceImpl extends LoginCommonServiceImpl implements ILoginService {

	@Autowired
	private IUserSimpleService userSimpleService;

	@Override
	@Transactional
	public LoginResult login(String username, String password, HttpServletResponse response) {
		// 登录
		User user = userService.login(username, password);
		return login(user, response);
	}

	@Override
	@Transactional
	public LoginResult loginSimple(LoginSimple vo, HttpServletResponse response) {
		// 检查验证码
		checkVerified(vo.getVerificationCodeCheck(), vo.getUsername());
		// 登录（简单版）
		User user = userService.loginSimple(vo.getUsername());
		return login(user, response);
	}

	protected LoginResult getLoginResult(User user) {
		LoginResult login = new LoginResult();
		// 用户编号
		login.setCode(user.getCode());
		// 邮箱
		login.setEmail(user.getEmail());
		// 手机
		login.setMobile(user.getMobile());
		if (null != user.getSettings()) {
			login.setSettings(user.getSettings());
		}
//		// 用户商户关联关系
//		UserMerchant userMerchant = userService.getUserMerchant(user.getCode());
//		// 商户编号
//		login.setMerchantCode(userMerchant.getMerchantCode());
//		// 商户名称
//		login.setMerchantName(userMerchant.getMerchantName());
//		// 商户品牌数量
//		login.setMerchantBrandCount(userMerchant.getMerchantBrandCount());
		// 用户简单信息
		UserSimple userSimple = userSimpleService.getUserSimple(user.getCode());
		if (null != userSimple) {
			// 商户名称
			login.setMerchantName(userSimple.getMerchantName());
			// 商户品牌数量
			login.setMerchantBrandCount(1);
		}
		return login;
	}

}
