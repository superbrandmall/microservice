package com.sbm.module.onlineleasing.customer.login.v2.biz.impl;

import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.onlineleasing.customer.login.biz.impl.LoginCommonServiceImpl;
import com.sbm.module.onlineleasing.customer.login.v2.biz.ILoginV2Service;
import com.sbm.module.onlineleasing.customer.user.v2.biz.IUserInfoService;
import com.sbm.module.onlineleasing.domain.login.LoginResult;
import com.sbm.module.onlineleasing.domain.login.LoginSimple;
import com.sbm.module.onlineleasing.domain.user.UserMerchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
public class LoginV2ServiceImpl extends LoginCommonServiceImpl implements ILoginV2Service {

	@Autowired
	private IUserInfoService userInfoService;

	@Override
	@Transactional
	public LoginResult login(LoginSimple vo, HttpServletResponse response) {
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
			// 语言
			login.setLang(user.getSettings().getLang());
			// 境内境外
			login.setInternational(user.getSettings().getInternational());
		}
		// 用户商户关联关系
		UserMerchant userMerchant = userInfoService.getUserMerchant(user.getCode());
		// 商户编号
		login.setMerchantCode(userMerchant.getMerchantCode());
		// 商户名称
		login.setMerchantName(userMerchant.getMerchantName());
		// 商户品牌数量
		login.setMerchantBrandCount(userMerchant.getMerchantBrandCount());
		return login;
	}

}
