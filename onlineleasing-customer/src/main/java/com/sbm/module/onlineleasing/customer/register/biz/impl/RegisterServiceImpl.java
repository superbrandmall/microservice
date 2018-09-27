package com.sbm.module.onlineleasing.customer.register.biz.impl;

import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.onlineleasing.customer.brand.biz.IBrandService;
import com.sbm.module.onlineleasing.customer.merchant.biz.IMerchantService;
import com.sbm.module.onlineleasing.customer.register.biz.IRegisterService;
import com.sbm.module.onlineleasing.customer.user.biz.IUserService;
import com.sbm.module.onlineleasing.domain.brand.ExistingBrand;
import com.sbm.module.onlineleasing.domain.brand.NewBrand;
import com.sbm.module.onlineleasing.domain.merchant.Merchant;
import com.sbm.module.onlineleasing.domain.register.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
public class RegisterServiceImpl extends RegisterCommonServiceImpl implements IRegisterService {

	@Autowired
	private IMerchantService merchantService;
	@Autowired
	private IBrandService brandService;

	/******************** 注册第一步 ********************/

	@Override
	@Transactional
	public StepOneResult stepOne(StepOne vo, HttpServletResponse response) {
		// 检查校验信息
		checkVerified(vo);
		// 注册用户
		User user = registerUser(vo);
		// 写入头参数
		setHeader(response, user);
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
		// 检查校验信息
		checkVerified(vo);
		// 注册用户
		User user = registerUser(vo);
		// 更新用户姓名
		userService.updateName(user.getCode(), vo.getUserName());
		// 插入simple表
		userService.saveUserSimple(user.getCode(), vo.getMerchantName(), vo.getBrandName(), vo.getModality(), vo.getWebsite(), vo.getFile());
		// 写入头参数
		setHeader(response, user);
		return mapOneIfNotNull(user, e -> new StepSimpleResult(e.getCode(), e.getEmail(), e.getMobile(), e.getSettings().getInternational()));
	}
}
