package com.sbm.module.onlineleasing.customer.user.biz.impl;

import com.sbm.module.common.authorization.api.user.constant.UserConstant;
import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.base.usersimple.biz.ITOLUserSimpleService;
import com.sbm.module.onlineleasing.base.usersimple.domain.TOLUserSimple;
import com.sbm.module.onlineleasing.customer.user.biz.IUserService;
import com.sbm.module.onlineleasing.customer.user.biz.IUserSimpleService;
import com.sbm.module.onlineleasing.domain.user.UserSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserSimpleServiceImpl extends CommonServiceImpl implements IUserSimpleService {

	@Autowired
	private IUserService userService;

	@Autowired
	private ITOLUserSimpleService userSimpleService;

	@Override
	@Transactional
	public void saveUserSimple(String userCode, String merchantName, String brandName, String modality, String website, String file) {
		TOLUserSimple po = userSimpleService.findOneByCode(userCode);
		if (null == po) {
			po = new TOLUserSimple();
			po.setCode(userCode);
		}
		po.setMerchantName(merchantName);
		po.setBrandName(brandName);
		po.setModality(modality);
		po.setWebsite(website);
		po.setFile(file);
		userSimpleService.save(po);
	}

	@Override
	public UserSimple getUserSimple(String userCode) {
		return mapOneIfNotNull(userSimpleService.findOneByCode(userCode),
				e -> mapOneIfNotNull(userService.findUserByUserCode(userCode),
						u -> new UserSimple(u.getCode(), u.getEmail(), u.getMobile(), /**密码不需要*/null, u.getLastLogin(), u.getEmailVerified(), u.getMobileVerified(), u.getSettings(),
								e.getMerchantName(), e.getBrandName(), e.getModality(), e.getWebsite(), e.getFile())));
	}

	@Override
	@Transactional
	public void saveUserSimple(UserSimple vo) {
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
		// 插入simple表
		saveUserSimple(vo.getCode(), vo.getMerchantName(), vo.getBrandName(), vo.getModality(), vo.getWebsite(), vo.getFile());
	}

}
