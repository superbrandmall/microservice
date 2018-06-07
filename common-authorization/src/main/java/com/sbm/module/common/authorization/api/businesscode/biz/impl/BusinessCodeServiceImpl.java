package com.sbm.module.common.authorization.api.businesscode.biz.impl;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.authorization.api.businesscode.biz.IBusinessCodeRegisterService;
import com.sbm.module.common.authorization.api.businesscode.biz.IBusinessCodeService;
import com.sbm.module.common.authorization.api.businesscode.domain.BusinessCode;
import com.sbm.module.common.authorization.api.businesscode.domain.BusinessCodeLang;
import com.sbm.module.common.authorization.base.businesscode.biz.ITCBusinessCodeService;
import com.sbm.module.common.authorization.base.businesscode.domain.TCBusinessCode;
import com.sbm.module.common.authorization.base.businesscodelang.biz.ITCBusinessCodeLangService;
import com.sbm.module.common.authorization.base.businesscodelang.domain.TCBusinessCodeLang;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.common.redis.constant.RedisConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
					// 存小写
					langPo.setLang(s.getLang().toLowerCase());
					langPo.setCustomerMessage(s.getCustomerMessage());
					businessCodeLangService.save(langPo);
				}
			});
		});
	}

	private BusinessCode convert(TCBusinessCode e) {
		BusinessCode vo = new BusinessCode(e.getCode(), e.getBusinessClazz(), e.getBusinessCode(), e.getMessage(), e.getCustomerMessage());
		List<BusinessCodeLang> langVos = map(businessCodeLangService.findAllByCode(e.getCode()), s -> new BusinessCodeLang(s.getLang(), s.getCustomerMessage()));
		vo.setBusinessCodeLangs(langVos);
		return vo;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<BusinessCode> findAll(Pageable pageable) {
		return businessCodeService.findAll(pageable).map(e -> convert(e));
	}

	private String getKey(String businessClazz, String businessCode) {
		return RedisConstant.getKey(BusinessCode.class, businessClazz, businessCode);
	}

	@Override
	@Scheduled(cron = "${sync.cron.businesscode}")
	public void refresh() {
		List<BusinessCode> vos = map(businessCodeService.findAll(), e -> convert(e));
		// 缓存业务代码列表
		for (BusinessCode vo : vos) {
			redisService.set2RedisTwoDays(getKey(vo.getBusinessClazz(), vo.getBusinessCode()), JSON.toJSONString(vo));
		}
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public BusinessCode findOneByBusinessClazzAndBusinessCode(String businessClazz, String businessCode) {
		BusinessCode vo = null;
		String valuer = (String) redisService.get(getKey(businessClazz, businessCode));
		if (StringUtils.isNotBlank(valuer)) {
			vo = JSON.parseObject(valuer, BusinessCode.class);
		}
		return vo;
	}
}
