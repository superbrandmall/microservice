package com.sbm.module.onlineleasing.base.shop.biz.impl;

import com.sbm.module.onlineleasing.base.serialcode.constant.SerialCodeConstant;
import com.sbm.module.onlineleasing.base.shop.biz.ITOLShopService;
import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import com.sbm.module.onlineleasing.base.shop.repository.ITOLShopRepository;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TOLShopServiceImpl extends OLDataServiceImpl<TOLShop, Integer> implements ITOLShopService {

	@Autowired
	private ITOLShopRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TOLShop findOneByHdUuid(String hdUuid) {
		return repository.findOneByHdUuid(hdUuid);
	}

	@Override
	@Transactional
	public <S extends TOLShop> S save(S po) {
		if (StringUtils.isEmpty(po.getCode())) {
			po.setCode(serialCodeService.next(SerialCodeConstant.OLSHOP).getNext());
		}
		return super.save(po);
	}
}
