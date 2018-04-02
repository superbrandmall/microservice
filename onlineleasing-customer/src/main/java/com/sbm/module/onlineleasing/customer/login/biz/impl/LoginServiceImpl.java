package com.sbm.module.onlineleasing.customer.login.biz.impl;

import com.sbm.module.common.authorization.api.jsonwebtoken.client.IJSONWebTokenClient;
import com.sbm.module.common.authorization.api.jsonwebtoken.constant.JSONWebTokenConstant;
import com.sbm.module.common.authorization.api.jsonwebtoken.domain.JSONWebToken;
import com.sbm.module.common.authorization.api.passport.client.IPassportClient;
import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.base.merchant.biz.ITOLMerchantService;
import com.sbm.module.onlineleasing.base.merchant.domain.TOLMerchant;
import com.sbm.module.onlineleasing.base.merchantbrand.biz.ITOLMerchantBrandService;
import com.sbm.module.onlineleasing.base.usermerchant.biz.ITOLUserMerchantService;
import com.sbm.module.onlineleasing.base.usermerchant.domain.TOLUserMerchant;
import com.sbm.module.onlineleasing.customer.login.biz.ILoginService;
import com.sbm.module.onlineleasing.domain.login.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class LoginServiceImpl extends CommonServiceImpl implements ILoginService {

	@Autowired
	private IPassportClient client;
	@Autowired
	private IJSONWebTokenClient jsonWebTokenClient;
	@Autowired
	private ITOLUserMerchantService userMerchantService;
	@Autowired
	private ITOLMerchantService merchantService;
	@Autowired
	private ITOLMerchantBrandService merchantBrandService;

	@Override
	public Login login(String username, String password, HttpServletResponse response) {
		JsonContainer<User> result = client.login(username, password);
		User user = checkJsonContainer(result);
		Login login = new Login();
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
		List<TOLUserMerchant> userMerchants = userMerchantService.findAllByUserCode(user.getCode());
		if (null != userMerchants && !userMerchants.isEmpty()) {
			// 目前用户商户1对1
			String merchantCode = userMerchants.get(0).getMerchantCode();
			// 商户编号
			login.setMerchantCode(merchantCode);
			// 商户名称
			TOLMerchant merchant = merchantService.findOneByCode(merchantCode);
			if (null != merchant) {
				login.setMerchantName(merchant.getName());
			}
			// 商户品牌数量
			login.setMerchantBrandCount(merchantBrandService.findAllByMerchantCode(merchantCode).size());
		}
		// 修改最后登陆时间
		client.updateLastLogin(user.getCode());
		// 写入头参数
		JsonContainer<String> token = jsonWebTokenClient.token(new JSONWebToken(user.getCode()));
		response.setHeader(JSONWebTokenConstant.AUTHORIZATION, checkJsonContainer(token));
		response.setHeader(JSONWebTokenConstant.LOGIN, user.getCode());
		return login;
	}
}
