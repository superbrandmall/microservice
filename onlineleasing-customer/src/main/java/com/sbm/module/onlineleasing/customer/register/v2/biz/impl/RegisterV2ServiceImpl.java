package com.sbm.module.onlineleasing.customer.register.v2.biz.impl;

import com.sbm.module.common.authorization.api.jsonwebtoken.client.IJSONWebTokenClient;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.customer.brand.biz.IBrandService;
import com.sbm.module.onlineleasing.customer.merchant.biz.IMerchantService;
import com.sbm.module.onlineleasing.customer.register.v2.biz.IRegisterV2Service;
import com.sbm.module.onlineleasing.customer.user.biz.IUserService;
import com.sbm.module.onlineleasing.customer.verify.biz.IVerifyService;
import com.sbm.module.onlineleasing.domain.register.StepSimple;
import com.sbm.module.onlineleasing.domain.register.StepSimpleResult;
import com.sbm.module.partner.tianyancha.rest.api736.client.IApi736Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
public class RegisterV2ServiceImpl extends CommonServiceImpl implements IRegisterV2Service {

	@Autowired
	private IJSONWebTokenClient jsonWebTokenClient;

	@Autowired
	private IUserService userService;
	@Autowired
	private IMerchantService merchantService;
	@Autowired
	private IVerifyService verifyService;
	@Autowired
	private IBrandService brandService;

	@Autowired
	private IApi736Client api736Client;

	private static String roleCode;

	/******************** 注册简单版 ********************/

	@Override
	@Transactional
	public StepSimpleResult register(StepSimple vo, HttpServletResponse response) {
//		// 手机验证
//		if (UserConstant.VERIFIED_MOBILE.equalsIgnoreCase(vo.getVerificationCodeCheck().getVerifyType())) {
//			verifyService.check(vo.getVerificationCodeCheck(), vo.getMobile());
//			vo.setMobileVerified(UserConstant.VERIFIED_1);
//		}
//		// 邮箱验证
//		else if (UserConstant.VERIFIED_EMAIL.equalsIgnoreCase(vo.getVerificationCodeCheck().getVerifyType())) {
//			verifyService.check(vo.getVerificationCodeCheck(), vo.getEmail());
//			vo.setEmailVerified(UserConstant.VERIFIED_1);
//		}
//		// 除此之外报错
//		else {
//			throw new BusinessException(VerificationCodeErrorCode.VC0004);
//		}
//
//		User user = userService.register(new Register(vo.getEmail(), vo.getMobile(), null, vo.getLang(), vo.getInternational(), vo.getEmailVerified(), vo.getMobileVerified()));
//		// 修改最后登陆时间
//		userService.updateLastLogin(user.getCode());
//		// 绑定默认用户角色
//		if (StringUtils.isBlank(roleCode))
//			roleCode = userService.findRoleByRole(RoleEnum.CUSTOMER.getRole().getRole()).getCode();
//		userService.saveUserRole(user.getCode(), roleCode);
//		// 更新用户姓名
//		userService.updateName(user.getCode(), vo.getUserName());
//		// 插入simple表
//		userService.saveUserSimple(user.getCode(), vo.getMerchantName(), vo.getBrandName(), vo.getModality(), vo.getWebsite(), vo.getFile());
//
//		// 写入头参数
//		JsonContainer<String> token = jsonWebTokenClient.token(new JSONWebToken(user.getCode()));
//		response.setHeader(JSONWebTokenConstant.AUTHORIZATION, checkJsonContainer(token));
//		response.setHeader(JSONWebTokenConstant.LOGIN, user.getCode());
//
//		return mapOneIfNotNull(user, e -> new StepSimpleResult(e.getCode(), e.getEmail(), e.getMobile(), e.getSettings().getInternational()));
		String result = api736Client.getCompanyByCode("91310118795683887K");
		System.out.println(result);
		return null;
	}
}
