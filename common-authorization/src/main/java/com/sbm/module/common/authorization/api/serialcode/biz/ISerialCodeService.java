package com.sbm.module.common.authorization.api.serialcode.biz;


import com.sbm.module.common.authorization.base.serialcode.domain.TCSerialCode;

public interface ISerialCodeService {

	TCSerialCode next(String serialGroup);
}
