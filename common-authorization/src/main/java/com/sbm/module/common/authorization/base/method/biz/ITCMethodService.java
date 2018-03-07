package com.sbm.module.common.authorization.base.method.biz;

import com.sbm.module.common.authorization.base.method.domain.TCMethod;
import com.sbm.module.common.data.biz.IDataService;

public interface ITCMethodService extends IDataService<TCMethod, Integer> {

	TCMethod findOneByApplicationNameAndMethodAndAndPattern(String applicationName, String method, String pattern);

}
