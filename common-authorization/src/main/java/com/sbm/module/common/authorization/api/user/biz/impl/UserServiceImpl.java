package com.sbm.module.common.authorization.api.user.biz.impl;

import com.sbm.module.common.authorization.api.user.biz.IUserService;
import com.sbm.module.common.authorization.api.user.constant.UserConstant;
import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.common.authorization.api.user.domain.UserSettings;
import com.sbm.module.common.authorization.base.user.biz.ITCUserService;
import com.sbm.module.common.authorization.base.user.domain.TCUser;
import com.sbm.module.common.authorization.base.usersettings.biz.ITCUserSettingsService;
import com.sbm.module.common.authorization.base.usersettings.domain.TCUserSettings;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.util.CodecUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserServiceImpl extends CommonServiceImpl implements IUserService {

	@Autowired
	private ITCUserService service;
	@Autowired
	private ITCUserSettingsService userSettingsService;

	@Override
	@Transactional
	public void save(User vo) {
		// 密码加密
		vo.setPassword(encryptPassword(vo.getPassword()));

		TCUser po = service.findOneByCode(vo.getCode());
		if (null == po) {
			po = service.newInstance();
		}
		po.setEmail(vo.getEmail());
		po.setMobile(vo.getMobile());
		po.setPassword(vo.getPassword());
		po.setLastLogin(vo.getLastLogin());
		po.setEmailVerified(vo.getEmailVerified());
		po.setMobileVerified(vo.getMobileVerified());
		service.save(po);
		// 设置code
		vo.setCode(po.getCode());
		// 保存settings
		TCUserSettings settings = userSettingsService.findOneByCode(po.getCode());
		if (null == settings) {
			settings = new TCUserSettings();
			settings.setCode(po.getCode());
		}
		settings.setName(vo.getSettings().getName());
		settings.setIdCard(vo.getSettings().getIdCard());
		settings.setIdCardType(vo.getSettings().getIdCardType());
		settings.setIdCardVerified(vo.getSettings().getIdCardVerified());
		settings.setIdCardFront(vo.getSettings().getIdCardFront());
		settings.setIdCardBack(vo.getSettings().getIdCardBack());
		settings.setLang(vo.getSettings().getLang());
		settings.setInternational(vo.getSettings().getInternational());
		userSettingsService.save(settings);
	}

	/**
	 * 密码加密
	 *
	 * @param password
	 */
	private String encryptPassword(String password) {
		return CodecUtil.sha1Hex(password);
	}

	/**
	 * 转换
	 *
	 * @param e
	 * @return
	 */
	private User convert(TCUser e) {
		return new User(e.getCode(), e.getEmail(), e.getMobile(), e.getPassword(), e.getLastLogin(), e.getEmailVerified(), e.getMobileVerified(),
				mapOneIfNotNull(userSettingsService.findOneByCode(e.getCode()), s -> new UserSettings(s.getName(), s.getIdCard(), s.getIdCardType(), s.getIdCardVerified(), s.getIdCardFront(), s.getIdCardBack(), s.getLang(), s.getInternational())));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<User> findAll(Pageable pageable) {
		return service.findAll(pageable).map(e -> convert(e));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public User findOneByCode(String code) {
		return mapOneIfNotNull(service.findOneByCode(code), e -> convert(e));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public User findOneByUsername(String username) {
		return mapOneIfNotNull(service.findOneByUsername(username), e -> convert(e));
	}

	@Override
	@Transactional
	public void updateLastLogin(String code) {
		TCUser po = service.findOneByCode(code);
		po.setLastLogin(new Date());
		service.save(po);
	}

	@Override
	@Transactional
	public void updateNameAndIdCard(String code, String name, String idCard, Integer idCardType) {
		TCUserSettings settings = userSettingsService.findOneByCode(code);
		if (null == settings) {
			settings = new TCUserSettings();
			settings.setCode(code);
		}
		settings.setName(name);
		settings.setIdCard(idCard);
		settings.setIdCardType(idCardType);
		settings.setIdCardVerified(UserConstant.VERIFIED_1);
		userSettingsService.save(settings);
	}

	@Override
	@Transactional
	public void updatePassword(String code, String password) {
		TCUser po = service.findOneByCode(code);
		po.setPassword(encryptPassword(password));
		service.save(po);
	}
}
