package com.sbm.module.onlineleasing.customer.user.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.base.merchant.biz.ITOLMerchantService;
import com.sbm.module.onlineleasing.base.merchantbrand.biz.ITOLMerchantBrandService;
import com.sbm.module.onlineleasing.base.usermerchant.biz.ITOLUserMerchantService;
import com.sbm.module.onlineleasing.base.usermerchant.domain.TOLUserMerchant;
import com.sbm.module.onlineleasing.customer.user.biz.IUserService;
import com.sbm.module.onlineleasing.domain.user.UserMerchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends CommonServiceImpl implements IUserService {

	@Autowired
	private ITOLUserMerchantService userMerchantService;
	@Autowired
	private ITOLMerchantService merchantService;
	@Autowired
	private ITOLMerchantBrandService merchantBrandService;

	@Override
	public UserMerchant getUserMerchant(String userCode) {
		UserMerchant userMerchant = new UserMerchant();
		List<TOLUserMerchant> userMerchants = userMerchantService.findAllByUserCode(userCode);
		if (null != userMerchants && !userMerchants.isEmpty()) {
			userMerchant.setUserCode(userCode);
			// 目前用户商户1对1
			String merchantCode = userMerchants.get(0).getMerchantCode();
			// 商户编号
			userMerchant.setMerchantCode(merchantCode);
			// 商户名称
			userMerchant.setMerchantName(mapOneIfNotNull(merchantService.findOneByCode(merchantCode), e -> e.getName()));
			// 商户品牌数量
			userMerchant.setMerchantBrandCount(merchantBrandService.findAllByMerchantCode(merchantCode).size());
		}
		return userMerchant;
	}

}
