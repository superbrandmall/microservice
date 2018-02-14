package com.sbm.module.onlineleasing.base.tempparam.biz.impl;

import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import com.sbm.module.onlineleasing.base.tempparam.biz.ITOLTempParamService;
import com.sbm.module.onlineleasing.base.tempparam.domain.TOLTempParam;
import com.sbm.module.onlineleasing.base.tempparam.repository.ITOLTempParamRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class TOLTempParamServiceImpl extends DataServiceImpl<TOLTempParam, Integer> implements ITOLTempParamService {

	@Autowired
	private ITOLTempParamRepository repository;

	private static final String PROPERTY_MESSAGE = "property is missing, brandUuid: {}, param: {}, value: {}";

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TOLTempParam findOneByParamAndValue(String param, String value) {
		return repository.findOneByParamAndValue(param, value);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Integer findKeyByParamAndValue(String param, String value, String hdUuid) {
		Integer key = null;
		if (StringUtils.isEmpty(value)) {
			return key;
		}
		TOLTempParam tempParam = findOneByParamAndValue(param, value);
		if (null != tempParam) {
			key = tempParam.getKey();
		} else {
			log.warn(PROPERTY_MESSAGE, hdUuid, param, value);
		}
		return key;
	}
}
