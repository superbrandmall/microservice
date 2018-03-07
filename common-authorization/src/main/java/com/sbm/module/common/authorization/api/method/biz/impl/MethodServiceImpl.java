package com.sbm.module.common.authorization.api.method.biz.impl;

import com.sbm.module.common.authorization.api.method.biz.IMethodService;
import com.sbm.module.common.authorization.api.method.domain.Method;
import com.sbm.module.common.authorization.base.method.biz.ITCMethodService;
import com.sbm.module.common.authorization.base.method.domain.TCMethod;
import com.sbm.module.common.redis.biz.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MethodServiceImpl implements IMethodService {

	@Autowired
	private IRedisService redisService;

	@Autowired
	private ITCMethodService service;

	/**
	 * 跳过
	 */
	private static final String PASS = "pass";
	/**
	 * 新增
	 */
	private static final String SAVE = "save";

	@Override
	@Transactional
	public void register(Method vo) {
		vo.getDetails().forEach(e -> {
			TCMethod po = service.findOneByApplicationNameAndMethodAndAndPattern(e.getApplicationName(), e.getMethod(), e.getPattern());
			// 存在则跳过
			if (po != null) {
				e.setOperate(PASS);
			}
			// 不存在则新增
			else {
				po = new TCMethod();
				po.setApplicationName(e.getApplicationName());
				po.setMethod(e.getMethod());
				po.setPattern(e.getPattern());
				po.setRemark(e.getRemark());
				service.save(po);
				e.setOperate(SAVE);
			}
		});
	}
}
