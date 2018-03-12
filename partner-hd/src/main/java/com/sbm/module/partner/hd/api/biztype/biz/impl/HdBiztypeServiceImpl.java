package com.sbm.module.partner.hd.api.biztype.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.partner.hd.api.biztype.biz.IHdBiztypeService;
import com.sbm.module.partner.hd.api.biztype.domain.HdBiztype;
import com.sbm.module.partner.hd.base.biztype.biz.IBiztypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HdBiztypeServiceImpl extends CommonServiceImpl implements IHdBiztypeService {

	@Autowired
	private IBiztypeService service;

	@Override
	public List<HdBiztype> findAll() {
		return map(service.findAll(), e -> new HdBiztype(e.getHdUuid(), e.getLevelid(), e.getCode(), e.getName(), e.getUpperid(), e.getRemark()));
	}

}
