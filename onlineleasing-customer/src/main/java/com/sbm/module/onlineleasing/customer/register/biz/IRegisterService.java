package com.sbm.module.onlineleasing.customer.register.biz;

import com.sbm.module.onlineleasing.domain.register.StepOne;
import com.sbm.module.onlineleasing.domain.register.StepOneResult;

import javax.servlet.http.HttpServletResponse;

public interface IRegisterService {

	StepOneResult stepOne(StepOne vo, HttpServletResponse response);

}
