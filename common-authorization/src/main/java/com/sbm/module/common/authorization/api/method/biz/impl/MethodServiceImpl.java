package com.sbm.module.common.authorization.api.method.biz.impl;

import com.sbm.module.common.authorization.api.method.biz.IMethodService;
import com.sbm.module.common.authorization.api.method.domain.Method;
import com.sbm.module.common.authorization.api.serialcode.biz.ISerialCodeService;
import com.sbm.module.common.authorization.base.method.biz.ITCMethodService;
import com.sbm.module.common.authorization.base.method.domain.TCMethod;
import com.sbm.module.common.authorization.init.SerialCodeInit;
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

	@Autowired
	private ISerialCodeService serialCodeService;

	@Autowired
	private SerialCodeInit serialCodeInit;

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
	public void register(Method vo) {
		// TODO 临时方案 先吧serialcode注册一遍
		serialCodeInit.init();

		vo.getDetails().forEach(e -> {
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
			po.setRemark(e.getRemark());
			service.save(po);
		});
	}
}
