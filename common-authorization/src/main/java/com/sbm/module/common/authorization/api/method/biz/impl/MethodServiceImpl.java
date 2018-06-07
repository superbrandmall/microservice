package com.sbm.module.common.authorization.api.method.biz.impl;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.authorization.api.method.biz.IMethodRegisterService;
import com.sbm.module.common.authorization.api.method.biz.IMethodService;
import com.sbm.module.common.authorization.api.method.domain.Method;
import com.sbm.module.common.authorization.api.method.provider.MethodProvider;
import com.sbm.module.common.authorization.base.method.biz.ITCMethodService;
import com.sbm.module.common.authorization.base.method.domain.TCMethod;
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
public class MethodServiceImpl extends CommonServiceImpl implements IMethodRegisterService, IMethodService {

	@Autowired
	private IRedisService redisService;
	@Autowired
	private ITCMethodService service;

	@Autowired
	private MethodProvider provider;

	/**
	 * 跳过
	 */
	private static final String UPDATE = "update";
	/**
	 * 新增
	 */
	private static final String SAVE = "save";

	private static final String KEY = RedisConstant.getKey(Method.class, RedisConstant.LIST);

	@Override
	@Transactional
	public void register(List<Method> vos) {
		vos.forEach(e -> {
			TCMethod po = service.findOneByApplicationNameAndMethodAndAndPattern(e.getApplicationName(), e.getMethod(), e.getPattern());
			if (null == po) {
				// 不存在新增
				po = service.newInstance();
				e.setOperate(SAVE);
			} else {
				// 存在更新
				e.setOperate(UPDATE);
			}
			e.setCode(po.getCode());

			po.setApplicationName(e.getApplicationName());
			po.setMethod(e.getMethod());
			po.setPattern(e.getPattern());
			// 只有校验标志为空时才赋值
			if (null == po.getValidFlag()) {
				po.setValidFlag(e.getValidFlag());
			} else {
				e.setValidFlag(po.getValidFlag());
			}
			po.setRemark(e.getRemark());
			service.save(po);
		});
	}

	private Method convert(TCMethod e) {
		return new Method(e.getCode(), e.getApplicationName(), e.getMethod(), e.getPattern(), e.getValidFlag(), e.getRemark());
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<Method> findAll(Pageable pageable) {
		return service.findAll(pageable).map(e -> convert(e));
	}

	@Override
	@Scheduled(cron = "${sync.cron.method}")
	public void refresh() {
		List<Method> vos = map(service.findAll(), e -> convert(e));
		// 缓存方法列表
		redisService.set2RedisTwoDays(KEY, JSON.toJSONString(vos));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Method findOneByPathAndMethod(String path, String method) {
		Method vo = null;
		String valuer = (String) redisService.get(KEY);
		if (StringUtils.isNotBlank(valuer)) {
			vo = JSON.parseArray(valuer, Method.class).stream().filter(e -> provider.match(path, method, e)).findFirst().orElse(null);
		}
		return vo;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Method> findAllByApplicationName(String applicationName) {
		return map(service.findAllByApplicationName(applicationName), e -> convert(e));
	}
}
