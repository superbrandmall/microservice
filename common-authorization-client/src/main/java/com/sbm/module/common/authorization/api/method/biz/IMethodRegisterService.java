package com.sbm.module.common.authorization.api.method.biz;


import com.sbm.module.common.authorization.api.method.domain.Method;

import java.util.List;

public interface IMethodRegisterService {

	/**
	 * 注册方法
	 *
	 * @param vos
	 */
	void register(List<Method> vos);

}
