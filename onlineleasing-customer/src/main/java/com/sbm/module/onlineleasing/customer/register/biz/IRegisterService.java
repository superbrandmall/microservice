package com.sbm.module.onlineleasing.customer.register.biz;

import com.sbm.module.onlineleasing.customer.register.domain.StepOne;
import com.sbm.module.onlineleasing.customer.register.domain.StepOneResult;

import javax.servlet.http.HttpServletResponse;

public interface IRegisterService {

	StepOneResult stepOne(StepOne vo, HttpServletResponse response);

}
