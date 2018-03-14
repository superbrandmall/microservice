package com.sbm.module.common.authorization.api.user.biz.impl;

import com.sbm.module.common.authorization.api.user.biz.IUserService;
import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.common.authorization.base.user.biz.ITCUserService;
import com.sbm.module.common.authorization.base.user.domain.TCUser;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends CommonServiceImpl implements IUserService {

	@Autowired
	private ITCUserService service;

	@Override
	@Transactional
	public void save(User vo) {
		TCUser po = service.findOneByCode(vo.getCode());
		if (null == po) {
			po = service.newInstance();
		}
		po.setEmail(vo.getEmail());
		po.setMobile(vo.getMobile());
		po.setLastLogin(vo.getLastLogin());
		po.setEmailVerified(vo.getEmailVerified());
		po.setEmailVerified(vo.getMobileVerified());
		service.save(po);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<User> findAll(Pageable pageable) {
		return service.findAll(pageable).map(e -> new User(e.getCode(), e.getEmail(), e.getMobile(), e.getLastLogin(), e.getEmailVerified(), e.getMobileVerified()));
	}
}
