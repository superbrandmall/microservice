package com.sbm.module.common.authorization.api.serialcode.biz;


import com.sbm.module.common.authorization.api.serialcode.domain.SerialCode;

import java.util.List;

public interface ISerialCodeRegisterService {

	/**
	 * 注册序列号
	 *
	 * @param vos
	 */
	void register(List<SerialCode> vos);

}
