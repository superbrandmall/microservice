package com.sbm.module.partner.hd.api.biztype.biz.impl;

import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import com.sbm.module.partner.hd.api.biztype.biz.IHdBiztypeService;
import com.sbm.module.partner.hd.api.biztype.domain.HdBiztype;
import com.sbm.module.partner.hd.base.biztype.domain.Biztype;
import org.springframework.stereotype.Service;

@Service
public class HdBiztypeServiceImpl extends DataServiceImpl<HdBiztype, Biztype, String> implements IHdBiztypeService {

	@Override
	public HdBiztype newInstance(Biztype e) {
		return new HdBiztype(e.getHdUuid(), e.getLevelid(), e.getCode(), e.getName(), e.getUpperid(), e.getRemark());
	}
}
