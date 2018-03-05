package com.sbm.module.onlineleasing.base.shop.biz.impl;

import com.sbm.module.onlineleasing.base.serialcode.constant.SerialCodeConstant;
import com.sbm.module.onlineleasing.base.shop.biz.ITOLShopService;
import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import com.sbm.module.onlineleasing.base.shop.repository.ITOLShopRepository;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class TOLShopServiceImpl extends OLDataServiceImpl<TOLShop, Integer> implements ITOLShopService {

	@Autowired
	private ITOLShopRepository repository;

	@Override
	public TOLShop newInstance() {
		TOLShop po = new TOLShop();
		po.setCode(serialCodeService.next(SerialCodeConstant.OLSHOP).getNext());
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TOLShop findOneByHdUuid(String hdUuid) {
		return repository.findOneByHdUuid(hdUuid);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TOLShop> findAllByFloorCodeInAndShopStateAndHdState(Collection<String> floorCodes, Integer shopState, String hdState) {
		return repository.findAllByFloorCodeInAndShopStateAndHdState(floorCodes, shopState, hdState);
	}
}
