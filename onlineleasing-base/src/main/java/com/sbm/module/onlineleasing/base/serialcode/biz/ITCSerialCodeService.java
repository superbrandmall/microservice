package com.sbm.module.onlineleasing.base.serialcode.biz;

import com.sbm.module.common.data.biz.IDataService;
import com.sbm.module.onlineleasing.base.serialcode.domain.TCSerialCode;

public interface ITCSerialCodeService extends IDataService<TCSerialCode, Integer> {

	TCSerialCode next(String serialGroup);

}
