package com.sbm.module.onlineleasing.customer.register.v2.biz;

import com.sbm.module.onlineleasing.domain.register.v2.StepV2;
import com.sbm.module.onlineleasing.domain.register.v2.StepV2Result;

import javax.servlet.http.HttpServletResponse;

public interface IRegisterV2Service {

	/******************** 注册 ********************/

	StepV2Result register(StepV2 vo, HttpServletResponse response);
}
