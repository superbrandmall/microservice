package com.sbm.module.common.authorization.base.serialcode.biz;

import com.sbm.module.common.authorization.base.serialcode.domain.TCSerialCode;
import com.sbm.module.common.data.biz.IDataService;

public interface ITCSerialCodeService extends IDataService<TCSerialCode, Integer> {

	TCSerialCode next(String serialGroup);

}
