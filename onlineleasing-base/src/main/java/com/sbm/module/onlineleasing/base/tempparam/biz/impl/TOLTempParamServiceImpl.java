package com.sbm.module.onlineleasing.base.tempparam.biz.impl;

import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import com.sbm.module.onlineleasing.base.tempparam.biz.ITOLTempParamService;
import com.sbm.module.onlineleasing.base.tempparam.domain.TOLTempParam;
import com.sbm.module.onlineleasing.base.tempparam.repository.ITOLTempParamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TOLTempParamServiceImpl extends DataServiceImpl<TOLTempParam, Integer> implements ITOLTempParamService {

	@Autowired
	private ITOLTempParamRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TOLTempParam findOneByParamAndValue(String param, String value) {
		return repository.findOneByParamAndValue(param, value);
	}
}
