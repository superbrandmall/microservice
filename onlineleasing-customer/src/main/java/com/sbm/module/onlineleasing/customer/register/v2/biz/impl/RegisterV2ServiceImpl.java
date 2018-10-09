package com.sbm.module.onlineleasing.customer.register.v2.biz.impl;

import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.common.exception.BusinessException;
import com.sbm.module.onlineleasing.customer.register.biz.impl.RegisterCommonServiceImpl;
import com.sbm.module.onlineleasing.customer.register.v2.biz.IRegisterV2Service;
import com.sbm.module.onlineleasing.domain.brand.Brand;
import com.sbm.module.onlineleasing.domain.merchant.Merchant;
import com.sbm.module.onlineleasing.domain.register.v2.StepV2;
import com.sbm.module.onlineleasing.domain.register.v2.StepV2Result;
import com.sbm.module.onlineleasing.domain.user.UserMerchant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
public class RegisterV2ServiceImpl extends RegisterCommonServiceImpl implements IRegisterV2Service {

	/******************** 注册简单版 ********************/

	@Override
	public StepV2Result register(StepV2 vo, HttpServletResponse response) {
		// 检查校验信息
		checkVerified(vo);
		// 获取商户信息
		Merchant merchant = getMerchant(vo.getMerchant());
		// 获取品牌信息
		Brand brand = getBrand(vo.getBrand());
		// 注册用户
		User user = registerUser(vo);
		// 返回结果
		StepV2Result result;
		try {
			// 更新用户姓名
			userService.updateName(user.getCode(), vo.getUserName());
			// 保存商户
			saveMerchant(merchant, user);
			// 保存品牌
			saveBrand(brand, merchant);
			// 写入头参数
			setHeader(response, user);
			// 转换返回结果
			result = convert(user);
		} catch (Exception e) {
			// 出错回滚
			userService.deleteByCode(user.getCode());
			throw e;
		}
		return result;
	}

	private StepV2Result convert(User user) {
		StepV2Result result = new StepV2Result();
		// 用户编号
		result.setCode(user.getCode());
		// 邮箱
		result.setEmail(user.getEmail());
		// 手机
		result.setMobile(user.getMobile());
		// 用户设置
		result.setSettings(user.getSettings());
		// 用户商户关联关系
		UserMerchant userMerchant = userInfoService.getUserMerchant(user.getCode());
		// 商户编号
		result.setMerchantCode(userMerchant.getMerchantCode());
		// 商户名称
		result.setMerchantName(userMerchant.getMerchantName());
		// 商户品牌数量
		result.setMerchantBrandCount(userMerchant.getMerchantBrandCount());
		// 品牌编号
		result.setBrandCode(userMerchant.getBrandCode());
		// 品牌名称
		result.setBrandName(userMerchant.getBrandName());
		// 品牌业态
		result.setBrandModality(userMerchant.getBrandModality());
		return result;
	}

}
