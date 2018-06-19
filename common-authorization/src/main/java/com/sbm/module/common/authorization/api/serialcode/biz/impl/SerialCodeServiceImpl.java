package com.sbm.module.common.authorization.api.serialcode.biz.impl;

import com.sbm.module.common.authorization.api.serialcode.biz.ISerialCodeRegisterService;
import com.sbm.module.common.authorization.api.serialcode.biz.ISerialCodeService;
import com.sbm.module.common.authorization.api.serialcode.domain.SerialCode;
import com.sbm.module.common.authorization.base.serialcode.biz.ITCSerialCodeService;
import com.sbm.module.common.authorization.base.serialcode.domain.TCSerialCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SerialCodeServiceImpl implements ISerialCodeService, ISerialCodeRegisterService {

	@Autowired
	private ITCSerialCodeService service;

	/**
	 * 更新
	 */
	private static final String UPDATE = "update";
	/**
	 * 新增
	 */
	private static final String SAVE = "save";

	@Override
	public String next(String serialGroup) {
		return service.next(serialGroup).getNext();
	}

	@Override
	@Transactional
	public void register(List<SerialCode> vos) {
		vos.forEach(e -> {
			TCSerialCode po = service.findBySerialGroup(e.getSerialGroup());
			if (null == po) {
				// 不存在新增
				po = new TCSerialCode();
				e.setOperate(SAVE);
			} else {
				// 存在更新
				e.setOperate(UPDATE);
			}
			po.setSerialGroup(e.getSerialGroup());
			po.setRemark(e.getRemark());
			service.save(po);
		});
	}
}
