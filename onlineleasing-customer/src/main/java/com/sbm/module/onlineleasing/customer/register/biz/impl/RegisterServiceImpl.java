package com.sbm.module.onlineleasing.customer.register.biz.impl;

import com.sbm.module.common.authorization.api.jsonwebtoken.client.IJSONWebTokenClient;
import com.sbm.module.common.authorization.api.jsonwebtoken.constant.JSONWebTokenConstant;
import com.sbm.module.common.authorization.api.jsonwebtoken.domain.JSONWebToken;
import com.sbm.module.common.authorization.api.passport.client.IPassportClient;
import com.sbm.module.common.authorization.api.passport.domain.Register;
import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.common.authorization.api.verificationcode.client.IVerificationCodeClient;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.exception.BusinessException;
import com.sbm.module.onlineleasing.base.merchant.biz.ITOLMerchantService;
import com.sbm.module.onlineleasing.base.merchant.domain.TOLMerchant;
import com.sbm.module.onlineleasing.base.merchantaddress.biz.ITOLMerchantAddressService;
import com.sbm.module.onlineleasing.base.merchantbusinesslicense.biz.ITOLMerchantBusinessLicenseService;
import com.sbm.module.onlineleasing.customer.register.biz.IRegisterService;
import com.sbm.module.onlineleasing.domain.register.*;
import com.sbm.module.onlineleasing.exception.OnlineleasingCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class RegisterServiceImpl extends CommonServiceImpl implements IRegisterService {

	@Autowired
	private IPassportClient passportClient;
	@Autowired
	private IJSONWebTokenClient jsonWebTokenClient;
	@Autowired
	private IVerificationCodeClient verificationCodeClient;

	@Autowired
	private ITOLMerchantService merchantService;
	@Autowired
	private ITOLMerchantAddressService merchantAddressService;
	@Autowired
	private ITOLMerchantBusinessLicenseService merchantBusinessLicenseService;

	/******************** 注册第一步 ********************/

	@Override
	public StepOneResult stepOne(StepOne vo, HttpServletResponse response) {
		// 检查验证码
		checkJsonContainer(verificationCodeClient.check(vo.getVerificationCodeCheck()));

		JsonContainer<User> result = passportClient.register(new Register(vo.getEmail(), vo.getMobile(), vo.getPassword(), vo.getLang(), vo.getInternational()));
		User user = checkJsonContainer(result);

		// 修改最后登陆时间
		passportClient.updateLastLogin(user.getCode());
		// 写入头参数
		JsonContainer<String> token = jsonWebTokenClient.token(new JSONWebToken(user.getCode()));
		response.setHeader(JSONWebTokenConstant.AUTHORIZATION, checkJsonContainer(token));
		response.setHeader(JSONWebTokenConstant.LOGIN, user.getCode());

		return mapOneIfNotNull(user, e -> new StepOneResult(e.getCode(), e.getEmail(), e.getMobile(), e.getSettings().getInternational()));
	}

	/******************** 注册第二步 ********************/

	@Override
	public StepTwoResult stepTwo(StepTwo vo) {
		return null;
	}

	@Override
	public StepTwoMerchantCheckResult stepTwoMerchantCheck(String uscc, String merchantName) {
		StepTwoMerchantCheckResult result;
		// 查询db
		TOLMerchant po = merchantService.findOneByUscc(uscc);
		// db中有
		if (null != po) {
			// 比较名称
			checkName(uscc, merchantName, po.getName());
			// 返回结果
			result = new StepTwoMerchantCheckResult(po.getCode(), po.getUscc(), po.getName(),
					mapOneIfNotNull(merchantBusinessLicenseService.findOneByCode(po.getCode()), e -> e.getBusinessLicense()),
					po.getCapital(),
					mapOneIfNotNull(merchantAddressService.findOneByCode(po.getCode()), e -> e.getStreetAddress()));
		}
		// db中没有，调用天眼查
		else {
			// TODO 天眼查暂无，返回不成功
			throw new RuntimeException("数据库没查到，天眼查没开通，暂时默认不通过");
		}
		return result;
	}

	private void checkName(String uscc, String merchantName, String _merchantName) {
		if (!merchantName.equals(_merchantName)) {
			throw new BusinessException(OnlineleasingCode.R0001, new Object[]{uscc, merchantName});
		}
	}

	/******************** 注册第三步 ********************/

}
