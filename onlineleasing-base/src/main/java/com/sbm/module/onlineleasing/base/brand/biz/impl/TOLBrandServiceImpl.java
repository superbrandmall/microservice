package com.sbm.module.onlineleasing.base.brand.biz.impl;

import com.sbm.module.onlineleasing.base.brand.biz.ITOLBrandService;
import com.sbm.module.onlineleasing.base.brand.domain.TOLBrand;
import com.sbm.module.onlineleasing.base.brand.repository.ITOLBrandRepository;
import com.sbm.module.onlineleasing.base.serialcode.constant.SerialCodeConstant;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TOLBrandServiceImpl extends OLDataServiceImpl<TOLBrand, Integer> implements ITOLBrandService {

	@Autowired
	private ITOLBrandRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TOLBrand findOneByHdUuid(String hdUuid) {
		return repository.findOneByHdUuid(hdUuid);
	}

	@Override
	@Transactional
	public <S extends TOLBrand> S save(S po) {
		if (StringUtils.isEmpty(po.getCode())) {
			po.setCode(serialCodeService.next(SerialCodeConstant.OLBRAND).getNext());
		}
		return super.save(po);
	}
}
