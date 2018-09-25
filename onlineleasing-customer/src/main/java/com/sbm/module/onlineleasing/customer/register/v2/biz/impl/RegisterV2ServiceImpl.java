package com.sbm.module.onlineleasing.customer.register.v2.biz.impl;

import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.onlineleasing.customer.brand.biz.IBrandService;
import com.sbm.module.onlineleasing.customer.register.biz.impl.RegisterCommonServiceImpl;
import com.sbm.module.onlineleasing.customer.register.v2.biz.IRegisterV2Service;
import com.sbm.module.onlineleasing.customer.user.biz.IUserService;
import com.sbm.module.onlineleasing.domain.merchant.Merchant;
import com.sbm.module.onlineleasing.domain.register.v2.StepV2;
import com.sbm.module.onlineleasing.domain.register.v2.StepV2Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
public class RegisterV2ServiceImpl extends RegisterCommonServiceImpl implements IRegisterV2Service {

	@Autowired
	private IUserService userService;
	@Autowired
	private IBrandService brandService;


	/******************** 注册简单版 ********************/

	@Override
	@Transactional
	public StepV2Result register(StepV2 vo, HttpServletResponse response) {
		// 检查校验信息
		checkVerified(vo);
		// 检查商户信息
		Merchant merchant = getMerchant(vo.getMerchant());
		// 注册用户
		User user = registerUser(vo);
		// 更新用户姓名
		userService.updateName(user.getCode(), vo.getUserName());
		// 保存商户
		saveMerchant(merchant, user);
		// 保存品牌
		saveBrand(vo.getBrand(), merchant);
		// 写入头参数
		setHeader(response, user);
//		return mapOneIfNotNull(user, e -> new StepSimpleResult(e.getCode(), e.getEmail(), e.getMobile(), e.getSettings().getInternational()));

		return null;
	}
}
