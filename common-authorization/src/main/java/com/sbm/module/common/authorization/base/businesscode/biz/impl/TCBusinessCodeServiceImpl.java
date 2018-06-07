package com.sbm.module.common.authorization.base.businesscode.biz.impl;

import com.sbm.module.common.authorization.api.serialcode.biz.ISerialCodeService;
import com.sbm.module.common.authorization.api.serialcode.constant.SerialCodeConstant;
import com.sbm.module.common.authorization.base.businesscode.biz.ITCBusinessCodeService;
import com.sbm.module.common.authorization.base.businesscode.domain.TCBusinessCode;
import com.sbm.module.common.authorization.base.businesscode.repository.ITCBusinessCodeRepository;
import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TCBusinessCodeServiceImpl extends DataServiceImpl<TCBusinessCode, Integer> implements ITCBusinessCodeService {

	@Autowired
	private ITCBusinessCodeRepository repository;

	@Autowired
	private ISerialCodeService serialCodeService;

	@Override
	public TCBusinessCode newInstance() {
		TCBusinessCode po = new TCBusinessCode();
		po.setCode(serialCodeService.next(SerialCodeConstant.CBUSINESSCODE));
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TCBusinessCode findOneByCode(String code) {
		return repository.findOneByCode(code);
	}

}
