package com.sbm.module.common.authorization.api.method.biz.impl;

import com.sbm.module.common.authorization.api.method.biz.IMethodRegisterService;
import com.sbm.module.common.authorization.api.method.biz.IMethodService;
import com.sbm.module.common.authorization.api.method.domain.Method;
import com.sbm.module.common.authorization.base.method.biz.ITCMethodService;
import com.sbm.module.common.authorization.base.method.domain.TCMethod;
import com.sbm.module.common.authorization.init.SerialCodeInit;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MethodServiceImpl extends CommonServiceImpl implements IMethodRegisterService, IMethodService {

	@Autowired
	private ITCMethodService service;

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
	public void register(List<Method> vos) {
		// TODO 临时方案 先吧serialcode注册一遍
		serialCodeInit.init();

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
			po.setRemark(e.getRemark());
			service.save(po);
		});
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<Method> findAll(Pageable pageable) {
		return service.findAll(pageable).map(e -> new Method(e.getCode(), e.getApplicationName(), e.getMethod(), e.getPattern(), e.getValidFlag(), e.getRemark()));
	}
}
