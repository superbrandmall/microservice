package com.sbm.module.onlineleasing.customer.register.v2.biz.impl;

import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.common.exception.BusinessException;
import com.sbm.module.onlineleasing.customer.register.biz.impl.RegisterCommonServiceImpl;
import com.sbm.module.onlineleasing.customer.register.v2.biz.IRegisterV2Service;
import com.sbm.module.onlineleasing.customer.user.biz.IUserService;
import com.sbm.module.onlineleasing.domain.brand.Brand;
import com.sbm.module.onlineleasing.domain.merchant.Merchant;
import com.sbm.module.onlineleasing.domain.register.v2.StepV2;
import com.sbm.module.onlineleasing.domain.register.v2.StepV2Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
public class RegisterV2ServiceImpl extends RegisterCommonServiceImpl implements IRegisterV2Service {

	/******************** 注册简单版 ********************/

	@Override
	@Transactional
	public StepV2Result register(StepV2 vo, HttpServletResponse response) {
		// 检查校验信息
//		checkVerified(vo);
		// 获取商户信息
		Merchant merchant = getMerchant(vo.getMerchant());
		// 获取品牌信息
		Brand brand = getBrand(vo.getBrand());
		// 注册用户
		User user = registerUser(vo);
		// 更新用户姓名
		userService.updateName(user.getCode(), vo.getUserName());
		try {
			// 保存商户
			saveMerchant(merchant, user);
			// 保存品牌
			saveBrand(brand, merchant);
		} catch (BusinessException e) {
			// 出错回滚
			userService.deleteByCode(user.getCode());
			throw e;
		}
		// 写入头参数
		setHeader(response, user);
		return mapOneIfNotNull(user, e -> new StepV2Result(e.getCode(), e.getEmail(), e.getMobile(), e.getSettings().getInternational()));
	}
}
