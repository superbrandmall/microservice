package com.sbm.module.onlineleasing.customer.register.biz;

import com.sbm.module.onlineleasing.domain.register.*;

import javax.servlet.http.HttpServletResponse;

public interface IRegisterService {

	/******************** 注册第一步 ********************/

	StepOneResult stepOne(StepOne vo, HttpServletResponse response);

	/******************** 注册第二步 ********************/

	StepTwoResult stepTwo(StepTwo vo);

	StepTwoMerchantCheckResult stepTwoMerchantCheck(String uscc, String merchantName);

	/******************** 注册第三步 ********************/

}
