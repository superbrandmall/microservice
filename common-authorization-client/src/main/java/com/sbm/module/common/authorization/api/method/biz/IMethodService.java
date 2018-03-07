package com.sbm.module.common.authorization.api.method.biz;


import com.sbm.module.common.authorization.api.method.domain.Method;

public interface IMethodService {

	/**
	 * 注册方法
	 *
	 * @param vo
	 */
	void register(Method vo);

}
