package com.sbm.module.common.authorization.api.businesscode.biz.impl;

import com.sbm.module.common.authorization.api.businesscode.biz.IBusinessCodeRegisterService;
import com.sbm.module.common.authorization.api.businesscode.biz.IBusinessCodeService;
import com.sbm.module.common.authorization.api.businesscode.domain.BusinessCode;
import com.sbm.module.common.authorization.api.method.domain.Method;
import com.sbm.module.common.authorization.base.businesscode.biz.ITCBusinessCodeService;
import com.sbm.module.common.authorization.base.businesscode.domain.TCBusinessCode;
import com.sbm.module.common.authorization.base.businesscodelang.biz.ITCBusinessCodeLangService;
import com.sbm.module.common.authorization.base.businesscodelang.domain.TCBusinessCodeLang;
import com.sbm.module.common.authorization.base.method.domain.TCMethod;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.common.redis.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BusinessCodeServiceImpl extends CommonServiceImpl implements IBusinessCodeRegisterService, IBusinessCodeService {

	@Autowired
	private IRedisService redisService;
	@Autowired
	private ITCBusinessCodeService businessCodeService;
	@Autowired
	private ITCBusinessCodeLangService businessCodeLangService;

	/**
	 * 跳过
	 */
	private static final String UPDATE = "update";
	/**
	 * 新增
	 */
	private static final String SAVE = "save";

	private static final String KEY = RedisConstant.getKey(BusinessCode.class, RedisConstant.LIST);

	@Override
	@Transactional
	public void register(List<BusinessCode> vos) {
		vos.forEach(e -> {
			TCBusinessCode po = businessCodeService.findOneByBusinessClazzAndBusinessCode(e.getBusinessClazz(), e.getBusinessCode());
			if (null == po) {
				// 不存在新增
				po = businessCodeService.newInstance();
				e.setOperate(SAVE);
			} else {
				// 存在更新
				e.setOperate(UPDATE);
			}
			e.setCode(po.getCode());

			po.setBusinessClazz(e.getBusinessClazz());
			po.setBusinessCode(e.getBusinessCode());
			po.setMessage(e.getMessage());
			po.setCustomerMessage(e.getCustomerMessage());
			businessCodeService.save(po);

			// 多语言表只新增不更新
			e.getBusinessCodeLangs().forEach(s -> {
				TCBusinessCodeLang langPo = businessCodeLangService.findOneByCodeAndLang(e.getCode(), s.getLang());
				if (null == langPo) {
					langPo = new TCBusinessCodeLang();
					langPo.setCode(e.getCode());
					langPo.setLang(s.getLang());
					langPo.setCustomerMessage(s.getCustomerMessage());
					businessCodeLangService.save(langPo);
				}
			});
		});
	}

	private Method convert(TCMethod e) {
		return new Method(e.getCode(), e.getApplicationName(), e.getMethod(), e.getPattern(), e.getValidFlag(), e.getRemark());
	}

}
