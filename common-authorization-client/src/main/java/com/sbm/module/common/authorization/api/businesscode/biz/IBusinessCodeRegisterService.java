package com.sbm.module.common.authorization.api.businesscode.biz;


import com.sbm.module.common.authorization.api.businesscode.domain.BusinessCode;

import java.util.List;

public interface IBusinessCodeRegisterService {

	/**
	 * 注册业务代码
	 *
	 * @param vos
	 */
	void register(List<BusinessCode> vos);

	/**
	 * 获取业务代码
	 *
	 * @param businessClazz
	 * @param businessCode
	 * @return
	 */
	BusinessCode findOneByBusinessClazzAndBusinessCode(String businessClazz, String businessCode);

}
