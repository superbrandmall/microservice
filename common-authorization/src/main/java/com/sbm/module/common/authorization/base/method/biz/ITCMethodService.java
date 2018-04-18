package com.sbm.module.common.authorization.base.method.biz;

import com.sbm.module.common.authorization.base.method.domain.TCMethod;
import com.sbm.module.common.data.biz.IDataService;

import java.util.List;

public interface ITCMethodService extends IDataService<TCMethod, Integer> {

	TCMethod newInstance();

	TCMethod findOneByApplicationNameAndMethodAndAndPattern(String applicationName, String method, String pattern);

	List<TCMethod> findAllByApplicationName(String applicationName);
}
