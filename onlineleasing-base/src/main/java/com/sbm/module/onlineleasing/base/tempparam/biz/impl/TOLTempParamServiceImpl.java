package com.sbm.module.onlineleasing.base.tempparam.biz.impl;

import com.sbm.module.common.data.biz.impl.JpaServiceImpl;
import com.sbm.module.onlineleasing.base.tempparam.biz.ITOLTempParamService;
import com.sbm.module.onlineleasing.base.tempparam.domain.TOLTempParam;
import com.sbm.module.onlineleasing.base.tempparam.repository.ITOLTempParamRepository;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TOLTempParamServiceImpl extends JpaServiceImpl<TOLTempParam, Integer> implements ITOLTempParamService {

	@Autowired
	private ITOLTempParamRepository repository;

}
