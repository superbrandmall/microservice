package com.sbm.module.common.authorization.api.serialcode.biz.impl;

import com.sbm.module.common.authorization.api.serialcode.biz.ISerialCodeService;
import com.sbm.module.common.authorization.base.serialcode.biz.ITCSerialCodeService;
import com.sbm.module.common.authorization.base.serialcode.domain.TCSerialCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SerialCodeServiceImpl implements ISerialCodeService {

	@Autowired
	private ITCSerialCodeService service;

	@Override
	public TCSerialCode next(String serialGroup) {
		return service.next(serialGroup);
	}
}
