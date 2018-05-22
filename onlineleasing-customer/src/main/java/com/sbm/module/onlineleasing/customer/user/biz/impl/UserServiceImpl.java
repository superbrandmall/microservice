package com.sbm.module.onlineleasing.customer.user.biz.impl;

import com.sbm.module.common.authorization.api.passport.client.IPassportCheckClient;
import com.sbm.module.common.authorization.api.passport.client.IPassportClient;
import com.sbm.module.common.authorization.api.passport.domain.Register;
import com.sbm.module.common.authorization.api.role.client.IRoleClient;
import com.sbm.module.common.authorization.api.role.domain.Role;
import com.sbm.module.common.authorization.api.user.constant.UserConstant;
import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.common.authorization.api.userrole.client.IUserRoleClient;
import com.sbm.module.common.authorization.api.userrole.domain.UserRole;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.exception.BusinessException;
import com.sbm.module.onlineleasing.base.usermerchant.biz.ITOLUserMerchantService;
import com.sbm.module.onlineleasing.base.usermerchant.domain.TOLUserMerchant;
import com.sbm.module.onlineleasing.base.usersimple.biz.ITOLUserSimpleService;
import com.sbm.module.onlineleasing.base.usersimple.domain.TOLUserSimple;
import com.sbm.module.onlineleasing.customer.merchant.biz.IMerchantService;
import com.sbm.module.onlineleasing.customer.user.biz.IUserService;
import com.sbm.module.onlineleasing.domain.user.UserMerchant;
import com.sbm.module.onlineleasing.domain.user.UserSimple;
import com.sbm.module.onlineleasing.exception.OnlineleasingCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends CommonServiceImpl implements IUserService {

	@Autowired
	private IPassportClient passportClient;
	@Autowired
	private IPassportCheckClient passportCheckClient;
	@Autowired
	private IUserRoleClient userRoleClient;
	@Autowired
	private IRoleClient roleClient;

	@Autowired
	private IMerchantService merchantService;
	@Autowired
	private ITOLUserMerchantService userMerchantService;

	@Autowired
	private ITOLUserSimpleService userSimpleService;


	@Override
	public User login(String username, String password) {
		return checkJsonContainer(passportClient.login(username, password));
	}

	@Override
	public User loginSimple(String username) {
		return checkJsonContainer(passportClient.loginSimple(username));
	}

	@Override
	@Transactional
	public void updateLastLogin(String userCode) {
		checkJsonContainer(passportClient.updateLastLogin(userCode));
	}

	@Override
	@Transactional
	public User register(Register register) {
		return checkJsonContainer(passportClient.register(register));
	}

	@Override
	public void existCode(String userCode) {
		checkJsonContainer(passportCheckClient.existCode(userCode));
	}

	@Override
	public User findUserByUserCode(String userCode) {
		return checkJsonContainer(passportClient.findOneByCode(userCode));
	}

	@Override
	@Transactional
	public void saveUserMerchant(String userCode, String merchantCode) {
		// 校验用户编号
		existCode(userCode);
		// 校验用户是否绑定商户（目前用户商户1对1）
		checkIfNotEmptyThrowException(userMerchantService.findAllByUserCode(userCode), new BusinessException(OnlineleasingCode.U0001, new Object[]{userCode}));
		// 校验用户和商户是否绑定（多校验一次，目前理论上不需要）
		userMerchantService.save(mapOneIfNotNullThrowException(userMerchantService.findOneByUserCodeAndMerchantCode(userCode, merchantCode), null,
				e -> new TOLUserMerchant(userCode, merchantCode),
				new BusinessException(OnlineleasingCode.U0002, new Object[]{userCode, merchantCode})));
	}

	@Override
	@Transactional
	public void updateNameAndIdCard(String userCode, String userName, String idCard, Integer idCardType) {
		checkJsonContainer(passportClient.updateNameAndIdCard(userCode, userName, idCard, idCardType));
	}

	@Override
	@Transactional
	public void updateName(String userCode, String userName) {
		checkJsonContainer(passportClient.updateName(userCode, userName));
	}

	@Override
	@Transactional
	public void updateUser(User vo) {
		checkJsonContainer(passportClient.updateUser(vo));
	}

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
			userMerchant.setMerchantBrandCount(merchantService.findBrandCountByMerchantCode(merchantCode));
		}
		return userMerchant;
	}

	@Override
	public Role findRoleByRole(String role) {
		return checkJsonContainer(roleClient.findOneByRole(role));
	}

	@Override
	@Transactional
	public void saveUserRole(String userCode, String roleCode) {
		List<UserRole> vos = new ArrayList<>();
		vos.add(new UserRole(userCode, roleCode));
		saveUserRole(vos);
	}

	@Override
	@Transactional
	public void saveUserRole(List<UserRole> vos) {
		checkJsonContainer(userRoleClient.save(vos));
	}

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
				e -> mapOneIfNotNull(findUserByUserCode(userCode),
						u -> new UserSimple(u.getCode(), u.getEmail(), u.getMobile(), /**密码不需要*/null, u.getLastLogin(), u.getEmailVerified(), u.getMobileVerified(), u.getSettings(),
								e.getMerchantName(), e.getBrandName(), e.getModality(), e.getWebsite(), e.getFile())));
	}

	@Override
	@Transactional
	public void saveUserSimple(UserSimple vo) {
		// 校验用户编号
		existCode(vo.getCode());
		// 查询用户原始信息
		User user = findUserByUserCode(vo.getCode());
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
		updateUser(user);
		// 插入simple表
		saveUserSimple(vo.getCode(), vo.getMerchantName(), vo.getBrandName(), vo.getModality(), vo.getWebsite(), vo.getFile());
	}
}
