package com.sbm.module.onlineleasing.customer.register.biz.impl;

import com.sbm.module.common.authorization.api.jsonwebtoken.client.IJSONWebTokenClient;
import com.sbm.module.common.authorization.api.jsonwebtoken.constant.JSONWebTokenConstant;
import com.sbm.module.common.authorization.api.jsonwebtoken.domain.JSONWebToken;
import com.sbm.module.common.authorization.api.passport.domain.Register;
import com.sbm.module.common.authorization.api.user.constant.UserConstant;
import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.common.authorization.exception.VerificationCodeErrorCode;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.exception.BusinessException;
import com.sbm.module.onlineleasing.customer.brand.biz.IBrandService;
import com.sbm.module.onlineleasing.customer.merchant.biz.IMerchantService;
import com.sbm.module.onlineleasing.customer.register.biz.IRegisterService;
import com.sbm.module.onlineleasing.customer.user.biz.IUserService;
import com.sbm.module.onlineleasing.customer.verify.biz.IVerifyService;
import com.sbm.module.onlineleasing.domain.brand.ExistingBrand;
import com.sbm.module.onlineleasing.domain.brand.NewBrand;
import com.sbm.module.onlineleasing.domain.merchant.Merchant;
import com.sbm.module.onlineleasing.domain.register.*;
import com.sbm.module.onlineleasing.exception.OnlineleasingCode;
import com.sbm.module.onlineleasing.init.RoleEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
public class RegisterServiceImpl extends CommonServiceImpl implements IRegisterService {

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

	private static String roleCode;

	/******************** 注册第一步 ********************/

	@Override
	@Transactional
	public StepOneResult stepOne(StepOne vo, HttpServletResponse response) {
		// 检查验证码 TODO 没改，参考simple
		if (UserConstant.INTERNATIONAL_0.equals(vo.getInternational())) {
			// 境内人士校验手机号
			verifyService.check(vo.getVerificationCodeCheck(), vo.getMobile());
		} else {
			// 境外人士校验邮箱
			verifyService.check(vo.getVerificationCodeCheck(), vo.getEmail());
		}

		User user = userService.register(new Register(vo.getEmail(), vo.getMobile(), vo.getPassword(), vo.getLang(), vo.getInternational(), vo.getEmailVerified(), vo.getMobileVerified()));
		// 修改最后登陆时间
		userService.updateLastLogin(user.getCode());
		// 绑定默认用户角色
		if (StringUtils.isBlank(roleCode))
			roleCode = userService.findRoleByRole(RoleEnum.CUSTOMER.getRole().getRole()).getCode();
		userService.saveUserRole(user.getCode(), roleCode);

		// 写入头参数
		JsonContainer<String> token = jsonWebTokenClient.token(new JSONWebToken(user.getCode()));
		response.setHeader(JSONWebTokenConstant.AUTHORIZATION, checkJsonContainer(token));
		response.setHeader(JSONWebTokenConstant.LOGIN, user.getCode());

		return mapOneIfNotNull(user, e -> new StepOneResult(e.getCode(), e.getEmail(), e.getMobile(), e.getSettings().getInternational()));
	}

	/******************** 注册第二步 ********************/

	@Override
	@Transactional
	public StepTwoResult stepTwo(StepTwo vo) {
		StepTwoResult result = new StepTwoResult(vo.getUserCode(), vo.getMerchantCode());
		// 更新商户类型
		merchantService.updateType(vo.getMerchantCode(), vo.getType());
		// 更新营业执照
		merchantService.updateBusinessLicense(vo.getMerchantCode(), vo.getBusinessLicense());
		// 绑定用户商户
		userService.saveUserMerchant(vo.getUserCode(), vo.getMerchantCode());
		// 更新用户证件信息
		userService.updateNameAndIdCard(vo.getUserCode(), vo.getUserName(), vo.getIdCard(), vo.getIdCardType());
		// 商户品牌数量
		result.setMerchantBrandCount(merchantService.findBrandCountByMerchantCode(vo.getMerchantCode()));
		return result;
	}

	@Override
	public StepTwoMerchantCheckResult stepTwoMerchantCheck(String uscc, String merchantName) {
		StepTwoMerchantCheckResult result;
		// 查询db
		Merchant vo = merchantService.findOneByUscc(uscc);
		// db中有
		if (null != vo) {
			// 检查名称
			checkName(uscc, merchantName, vo.getName());
			// 返回结果
			result = new StepTwoMerchantCheckResult(vo.getCode(), vo.getUscc(), vo.getName(), vo.getType(), vo.getBusinessLicense(),
					vo.getCapital(), vo.getAddress().getStreetAddress());
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

	@Override
	public StepThreeResult stepThreeAddNewBrand(StepThree<NewBrand> vo) {
		brandService.addNewBrand(vo.getBrand());
		return new StepThreeResult(merchantService.findBrandCountByMerchantCode(vo.getBrand().getMerchantCode()));
	}

	@Override
	public StepThreeResult stepThreeAddExistingBrand(StepThree<ExistingBrand> vo) {
		brandService.addExistingBrand(vo.getBrand());
		return new StepThreeResult(merchantService.findBrandCountByMerchantCode(vo.getBrand().getMerchantCode()));
	}

	/******************** 注册简单版 ********************/

	@Override
	@Transactional
	public StepSimpleResult stepSimple(StepSimple vo, HttpServletResponse response) {
//		// 检查验证码
//		if (UserConstant.INTERNATIONAL_0.equals(vo.getInternational())) {
//			// 境内人士校验手机号
//			verifyService.check(vo.getVerificationCodeCheck(), vo.getMobile());
//		} else {
//			// 境外人士校验邮箱
//			verifyService.check(vo.getVerificationCodeCheck(), vo.getEmail());
//		}
		// 手机验证
		if (UserConstant.VERIFIED_1.equals(vo.getMobileVerified()) && UserConstant.VERIFIED_0.equals(vo.getEmailVerified())) {
			verifyService.check(vo.getVerificationCodeCheck(), vo.getMobile());
		}
		// 邮箱验证
		else if (UserConstant.VERIFIED_0.equals(vo.getMobileVerified()) && UserConstant.VERIFIED_1.equals(vo.getEmailVerified())) {
			verifyService.check(vo.getVerificationCodeCheck(), vo.getEmail());
		}
		// 除此之外报错
		else {
			throw new BusinessException(VerificationCodeErrorCode.VC0004);
		}

		User user = userService.register(new Register(vo.getEmail(), vo.getMobile(), null, vo.getLang(), vo.getInternational(), vo.getEmailVerified(), vo.getMobileVerified()));
		// 修改最后登陆时间
		userService.updateLastLogin(user.getCode());
		// 绑定默认用户角色
		if (StringUtils.isBlank(roleCode))
			roleCode = userService.findRoleByRole(RoleEnum.CUSTOMER.getRole().getRole()).getCode();
		userService.saveUserRole(user.getCode(), roleCode);
		// 更新用户姓名
		userService.updateName(user.getCode(), vo.getUserName());
		// 插入simple表
		userService.saveUserSimple(user.getCode(), vo.getMerchantName(), vo.getBrandName(), vo.getModality(), vo.getWebsite(), vo.getFile());

		// 写入头参数
		JsonContainer<String> token = jsonWebTokenClient.token(new JSONWebToken(user.getCode()));
		response.setHeader(JSONWebTokenConstant.AUTHORIZATION, checkJsonContainer(token));
		response.setHeader(JSONWebTokenConstant.LOGIN, user.getCode());

		return mapOneIfNotNull(user, e -> new StepSimpleResult(e.getCode(), e.getEmail(), e.getMobile(), e.getSettings().getInternational()));
	}
}
