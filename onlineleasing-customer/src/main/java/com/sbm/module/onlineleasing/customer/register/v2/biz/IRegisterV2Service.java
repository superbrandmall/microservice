package com.sbm.module.onlineleasing.customer.register.v2.biz;

import com.sbm.module.onlineleasing.domain.register.StepSimple;
import com.sbm.module.onlineleasing.domain.register.StepSimpleResult;

import javax.servlet.http.HttpServletResponse;

public interface IRegisterV2Service {

	/******************** 注册 ********************/

	StepSimpleResult register(StepSimple vo, HttpServletResponse response);
}
