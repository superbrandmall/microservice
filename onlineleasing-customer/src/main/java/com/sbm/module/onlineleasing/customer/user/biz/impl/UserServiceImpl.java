package com.sbm.module.onlineleasing.customer.user.biz.impl;

import com.sbm.module.common.authorization.api.passport.client.IPassportCheckClient;
import com.sbm.module.common.authorization.api.passport.client.IPassportClient;
import com.sbm.module.common.authorization.api.passport.domain.Register;
import com.sbm.module.common.authorization.api.role.client.IRoleClient;
import com.sbm.module.common.authorization.api.role.domain.Role;
import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.common.authorization.api.userrole.client.IUserRoleClient;
import com.sbm.module.common.authorization.api.userrole.domain.UserRole;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.base.usermerchant.biz.ITOLUserMerchantService;
import com.sbm.module.onlineleasing.customer.merchant.biz.IMerchantService;
import com.sbm.module.onlineleasing.customer.user.biz.IUserService;
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
	public void deleteByCode(String code) {
		checkJsonContainer(passportClient.deleteByCode(code));
	}
}
