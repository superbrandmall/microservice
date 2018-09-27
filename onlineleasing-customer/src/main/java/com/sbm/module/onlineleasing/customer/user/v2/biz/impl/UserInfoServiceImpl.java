package com.sbm.module.onlineleasing.customer.user.v2.biz.impl;

import com.sbm.module.common.authorization.api.user.constant.UserConstant;
import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.exception.BusinessException;
import com.sbm.module.onlineleasing.base.usermerchant.biz.ITOLUserMerchantService;
import com.sbm.module.onlineleasing.base.usermerchant.domain.TOLUserMerchant;
import com.sbm.module.onlineleasing.customer.brand.biz.IBrandService;
import com.sbm.module.onlineleasing.customer.merchant.biz.IMerchantService;
import com.sbm.module.onlineleasing.customer.user.biz.IUserService;
import com.sbm.module.onlineleasing.customer.user.v2.biz.IUserInfoService;
import com.sbm.module.onlineleasing.domain.brand.MerchantBrand;
import com.sbm.module.onlineleasing.domain.user.UserMerchant;
import com.sbm.module.onlineleasing.exception.OnlineleasingCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserInfoServiceImpl extends CommonServiceImpl implements IUserInfoService {

	@Autowired
	private IUserService userService;
	@Autowired
	private IMerchantService merchantService;
	@Autowired
	private IBrandService brandService;
	@Autowired
	private ITOLUserMerchantService userMerchantService;

	@Override
	@Transactional
	public void saveUserMerchant(String userCode, String merchantCode) {
		// 校验用户编号
		userService.existCode(userCode);
		// 校验用户是否绑定商户（目前用户商户1对1）
		checkIfNotEmptyThrowException(userMerchantService.findAllByUserCode(userCode), new BusinessException(OnlineleasingCode.U0001, new Object[]{userCode}));
		// 校验用户和商户是否绑定（多校验一次，目前理论上不需要）
		userMerchantService.save(mapOneIfNotNullThrowException(userMerchantService.findOneByUserCodeAndMerchantCode(userCode, merchantCode), null,
				e -> new TOLUserMerchant(userCode, merchantCode),
				new BusinessException(OnlineleasingCode.U0002, new Object[]{userCode, merchantCode})));
	}


	@Override
	public UserMerchant getUserMerchant(String userCode) {
		return mapOneIfNotNull(userService.findUserByUserCode(userCode), e -> {
			UserMerchant userMerchant = new UserMerchant(e.getCode(), e.getEmail(), e.getMobile(), e.getPassword(), e.getLastLogin(), e.getEmailVerified(), e.getMobileVerified(), e.getSettings());
			List<TOLUserMerchant> userMerchants = userMerchantService.findAllByUserCode(userCode);
			if (null != userMerchants && !userMerchants.isEmpty()) {
				// 目前用户商户1对1
				String merchantCode = userMerchants.get(0).getMerchantCode();
				// 商户编号
				userMerchant.setMerchantCode(merchantCode);
				// 商户名称
				userMerchant.setMerchantName(mapOneIfNotNull(merchantService.findOneByCode(merchantCode), s -> s.getName()));
				// 商户品牌数量
				userMerchant.setMerchantBrandCount(merchantService.findBrandCountByMerchantCode(merchantCode));
				// 品牌数量大于0 取第一个
				if (userMerchant.getMerchantBrandCount() > 0) {
					MerchantBrand merchantBrand = brandService.findAllByMerchantCode(merchantCode).get(0);
					// 品牌编号
					userMerchant.setBrandCode(merchantBrand.getBrandCode());
					// 品牌名称
					userMerchant.setBrandName(merchantBrand.getBrandName());
					// 品牌业态
					userMerchant.setBrandModality(merchantBrand.getBrandModality());
				}
			}
			return userMerchant;
		});
	}

	@Override
	@Transactional
	public void saveUserMerchant(UserMerchant vo) {
		// 校验用户编号
		userService.existCode(vo.getCode());
		// 查询用户原始信息
		User user = userService.findUserByUserCode(vo.getCode());
		// 更新用户信息，目前只更新部分
		if (UserConstant.VERIFIED_0.equals(user.getMobileVerified())) {
			// 用户原始手机未校验，则可以更新
			user.setMobile(vo.getMobile());
		}
		if (UserConstant.VERIFIED_0.equals(user.getEmailVerified())) {
			// 用户原始邮箱未校验，则可以更新
			user.setEmail(vo.getEmail());
		}
		user.setSettings(vo.getSettings());
		userService.updateUser(user);
		// 其他信息暂不修改
	}
}
