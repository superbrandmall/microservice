package com.sbm.module.common.authorization.base.businesscode.biz;

import com.sbm.module.common.authorization.base.businesscode.domain.TCBusinessCode;
import com.sbm.module.common.data.biz.IDataService;

public interface ITCBusinessCodeService extends IDataService<TCBusinessCode, Integer> {

	TCBusinessCode newInstance();

	TCBusinessCode findOneByCode(String code);

	TCBusinessCode findOneByBusinessClazzAndBusinessCode(String businessClazz, String businessCode);
}
