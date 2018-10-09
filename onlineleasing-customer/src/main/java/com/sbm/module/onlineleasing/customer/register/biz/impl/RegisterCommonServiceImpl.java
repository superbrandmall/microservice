package com.sbm.module.onlineleasing.customer.register.biz.impl;

import com.sbm.module.common.authorization.api.jsonwebtoken.client.IJSONWebTokenClient;
import com.sbm.module.common.authorization.api.jsonwebtoken.constant.JSONWebTokenConstant;
import com.sbm.module.common.authorization.api.jsonwebtoken.domain.JSONWebToken;
import com.sbm.module.common.authorization.api.passport.domain.Register;
import com.sbm.module.common.authorization.api.user.constant.UserConstant;
import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.common.authorization.api.verificationcode.domain.VerificationCodeCheck;
import com.sbm.module.common.authorization.exception.VerificationCodeErrorCode;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.exception.BusinessException;
import com.sbm.module.onlineleasing.base.brand.constant.BrandConstant;
import com.sbm.module.onlineleasing.constant.HdConstant;
import com.sbm.module.onlineleasing.customer.base.modality.biz.IModalityService;
import com.sbm.module.onlineleasing.customer.brand.biz.IBrandService;
import com.sbm.module.onlineleasing.customer.merchant.biz.IMerchantService;
import com.sbm.module.onlineleasing.customer.user.biz.IUserService;
import com.sbm.module.onlineleasing.customer.user.v2.biz.IUserInfoService;
import com.sbm.module.onlineleasing.customer.verify.biz.IVerifyService;
import com.sbm.module.onlineleasing.domain.base.modality.ModalityMaxInfo;
import com.sbm.module.onlineleasing.domain.brand.Brand;
import com.sbm.module.onlineleasing.domain.merchant.Merchant;
import com.sbm.module.onlineleasing.domain.register.StepOne;
import com.sbm.module.onlineleasing.exception.OnlineleasingCode;
import com.sbm.module.onlineleasing.init.RoleEnum;
import com.sbm.module.partner.hd.rest.base.domain.HdBizType;
import com.sbm.module.partner.hd.rest.base.domain.HdResult;
import com.sbm.module.partner.hd.rest.brand.client.IHdBrandClient;
import com.sbm.module.partner.hd.rest.brand.domain.HdBrand;
import com.sbm.module.partner.hd.rest.merchant.client.IHdMerchantClient;
import com.sbm.module.partner.hd.rest.merchant.domain.HdBank;
import com.sbm.module.partner.hd.rest.merchant.domain.HdMerchant;
import com.sbm.module.partner.hd.rest.merchant.domain.HdMerchantProperties;
import com.sbm.module.partner.tianyancha.rest.api736.client.IApi736Client;
import com.sbm.module.partner.tianyancha.rest.api736.domain.Api736;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class RegisterCommonServiceImpl extends CommonServiceImpl {

	@Autowired
	protected IUserInfoService userInfoService;
	@Autowired
	protected IUserService userService;

	@Autowired
	private IVerifyService verifyService;
	@Autowired
	private IJSONWebTokenClient jsonWebTokenClient;
	@Autowired
	private IMerchantService merchantService;
	@Autowired
	private IBrandService brandService;
	@Autowired
	private IApi736Client api736Client;
	@Autowired
	private IHdMerchantClient hdMerchantClient;
	@Autowired
	private IHdBrandClient hdBrandClient;
	@Autowired
	private IModalityService modalityService;

	private static String roleCode;

	/**
	 * 检查校验信息
	 *
	 * @param vo
	 */
	protected void checkVerified(StepOne vo) {
		// 手机验证
		if (UserConstant.VERIFIED_MOBILE.equalsIgnoreCase(vo.getVerificationCodeCheck().getVerifyType())) {
			checkVerified(vo.getVerificationCodeCheck(), vo.getMobile());
			vo.setMobileVerified(UserConstant.VERIFIED_1);
		}
		// 邮箱验证
		else if (UserConstant.VERIFIED_EMAIL.equalsIgnoreCase(vo.getVerificationCodeCheck().getVerifyType())) {
			checkVerified(vo.getVerificationCodeCheck(), vo.getEmail());
			vo.setEmailVerified(UserConstant.VERIFIED_1);
		}
		// 除此之外报错
		else {
			throw new BusinessException(VerificationCodeErrorCode.VC0004);
		}
	}

	/**
	 * 检查校验信息
	 *
	 * @param check
	 * @param keyword
	 */
	protected void checkVerified(VerificationCodeCheck check, String keyword) {
		verifyService.check(check, keyword);
	}

	/**
	 * 注册用户
	 *
	 * @param vo
	 * @return
	 */
	protected User registerUser(StepOne vo) {
		// 注册用户
		User user = userService.register(new Register(vo.getEmail(), vo.getMobile(), vo.getPassword(), vo.getLang(), vo.getInternational(), vo.getEmailVerified(), vo.getMobileVerified()));
		// 修改最后登陆时间
		userService.updateLastLogin(user.getCode());
		// 绑定默认用户角色
		if (StringUtils.isBlank(roleCode)) {
			roleCode = userService.findRoleByRole(RoleEnum.CUSTOMER.getRole().getRole()).getCode();
		}
		userService.saveUserRole(user.getCode(), roleCode);
		return user;
	}

	/**
	 * 写入头参数
	 *
	 * @param response
	 * @param user
	 */
	protected void setHeader(HttpServletResponse response, User user) {
		// 写入头参数
		JsonContainer<String> token = jsonWebTokenClient.token(new JSONWebToken(user.getCode()));
		response.setHeader(JSONWebTokenConstant.AUTHORIZATION, checkJsonContainer(token));
		response.setHeader(JSONWebTokenConstant.LOGIN, user.getCode());
	}

	/**
	 * 获取商户信息
	 *
	 * @param tmp
	 * @return
	 */
	protected Merchant getMerchant(Merchant tmp) {
		// 查询db
		Merchant merchant = merchantService.findOneByUscc(tmp.getUscc());
		// 查询天眼查
		if (null == merchant) {
			Api736 result = api736Client.getCompanyByCode(tmp.getUscc()).getResult();
			if (null != result) {
				merchant = tmp;
				merchant.setCode(null);
				merchant.setTianyanchaId(result.getId());
			}
		}
		// 检查企业名称
		if (null != merchant) {
			checkName(tmp.getUscc(), tmp.getName(), merchant.getName());
		}
		// 查询不到商户信息
		else {
			throw new BusinessException(OnlineleasingCode.R0002, new Object[]{tmp.getUscc(), tmp.getName()});
		}
		return merchant;
	}

	/**
	 * 检查企业名称
	 *
	 * @param uscc
	 * @param merchantName
	 * @param dbMerchantName
	 */
	protected void checkName(String uscc, String merchantName, String dbMerchantName) {
		if (!merchantName.equals(dbMerchantName)) {
			throw new BusinessException(OnlineleasingCode.R0001, new Object[]{uscc, merchantName});
		}
	}

	/**
	 * 保存商户
	 *
	 * @param merchant
	 * @param user
	 */
	protected void saveMerchant(Merchant merchant, User user) {
		if (StringUtils.isBlank(merchant.getCode())) {
			// 商户信息
			HdMerchant hdMerchant = new HdMerchant();
			hdMerchant.setName(merchant.getName());
			hdMerchant.setState(HdConstant.HD_STATE_USING);
			hdMerchant.setType("merchant");
			// 商户配置信息
			HdMerchantProperties hdMerchantProperties = new HdMerchantProperties();
			hdMerchantProperties.setUscc(merchant.getUscc());
			hdMerchantProperties.setTianyancha_id(merchant.getTianyanchaId().toString());
			hdMerchant.setProperties(hdMerchantProperties);
			// 商户银行账号信息
			List<HdBank> hdBanks = new ArrayList<>();
			HdBank hdBank = new HdBank("OL默认", "OL默认", "系统默认");
			hdBanks.add(hdBank);
			hdMerchant.setBanks(hdBanks);
			// 存入海鼎
			HdResult<HdMerchant> result = hdMerchantClient.save(hdMerchant);
			checkIfNullThrowException(result.getBody(), new BusinessException(OnlineleasingCode.M0003, null, result));
			hdMerchant = result.getBody();
			// 海鼎状态
			merchant.setHdUuid(hdMerchant.getUuid());
			merchant.setHdCode(hdMerchant.getCode());
			merchant.setHdState(hdMerchant.getState());
			// 保存铺位
			merchantService.save(merchant);
		}
		// 绑定用户商户
		userInfoService.saveUserMerchant(user.getCode(), merchant.getCode());
	}

	/**
	 * 获取品牌信息
	 *
	 * @param tmp
	 * @return
	 */
	protected Brand getBrand(Brand tmp) {
		// 查询db
		Brand brand = brandService.findOneByName(tmp.getName());
		// 为空则新增
		if (null == brand) {
			brand = tmp;
			brand.setCode(null);
		}
		return brand;
	}

	/**
	 * 保存品牌
	 *
	 * @param brand
	 * @param merchant
	 */
	protected void saveBrand(Brand brand, Merchant merchant) {
		if (StringUtils.isBlank(brand.getCode())) {
			// 品牌信息
			HdBrand hdBrand = new HdBrand();
			hdBrand.setName(brand.getName());
			hdBrand.setState(HdConstant.HD_STATE_USING);
			// 业态信息
			ModalityMaxInfo modalityMaxInfo = modalityService.findOneByCode(brand.getModality_3());
			checkIfNullThrowException(modalityMaxInfo, new BusinessException(OnlineleasingCode.B0005, new Object[]{brand.getModality_3()}));
			HdBizType hdBizType = new HdBizType();
			hdBizType.setName(modalityMaxInfo.getName());
			hdBizType.setLevelId(modalityMaxInfo.getHdLevelid());
			hdBizType.setCode(modalityMaxInfo.getCode());
			hdBizType.setUuid(modalityMaxInfo.getHdUuid());
			hdBrand.setBizType(hdBizType);
			// 存入海鼎
			HdResult<HdBrand> result = hdBrandClient.save(hdBrand);
			checkIfNullThrowException(result.getBody(), new BusinessException(OnlineleasingCode.B0004, null, result));
			hdBrand = result.getBody();
			// 海鼎状态
			brand.setStatus(BrandConstant.ADMITTANCE);
			brand.setHdUuid(hdBrand.getUuid());
			brand.setHdCode(hdBrand.getCode());
			brand.setHdState(hdBrand.getState());
			// 保存品牌
			brandService.save(brand);
		}
		// 商户品牌关系
		brandService.saveMerchantBrand(merchant.getCode(), brand.getCode());
	}

}
