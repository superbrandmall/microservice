package com.sbm.module.onlineleasing.base.mall.biz.impl;

import com.sbm.module.onlineleasing.base.mall.biz.ITOLMallService;
import com.sbm.module.onlineleasing.base.mall.domain.TOLMall;
import com.sbm.module.onlineleasing.base.mall.repository.ITOLMallRepository;
import com.sbm.module.onlineleasing.base.serialcode.constant.SerialCodeConstant;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TOLMallServiceImpl extends OLDataServiceImpl<TOLMall, Integer> implements ITOLMallService {

	@Autowired
	private ITOLMallRepository repository;

	@Override
	public TOLMall newInstance() {
		TOLMall po = new TOLMall();
		po.setCode(serialCodeService.next(SerialCodeConstant.OLMALL).getNext());
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TOLMall findOneByHdUuid(String hdUuid) {
		return repository.findOneByHdUuid(hdUuid);
	}

}
