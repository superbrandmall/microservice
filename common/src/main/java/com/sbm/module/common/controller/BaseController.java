package com.sbm.module.common.controller;

import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.exception.BusinessCode;
import com.sbm.module.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseController {

	/************** 创建json容器 ***************/
	public JsonContainer getJsonContainer() {
		return new JsonContainer();
	}

	/************** 返回成功信息 ***************/
	public <T> JsonContainer<T> setSuccessMessage() {
		return setSuccessMessage(null);
	}

	public <T> JsonContainer<T> setSuccessMessage(T data) {
		return setSuccessMessage(getJsonContainer(), data);
	}

	public <T> JsonContainer<T> setSuccessMessage(JsonContainer jsonContainer, T data) {
		jsonContainer.set(BusinessCode.C0);
		jsonContainer.setData(data);
		return jsonContainer;
	}

	/************** 返回失败信息 ***************/
	public <T> JsonContainer<T> setErrorMessage(Exception e) {
		return setErrorMessage(getJsonContainer(), e, null);
	}

	public <T> JsonContainer<T> setErrorMessage(Exception e, T data) {
		return setErrorMessage(getJsonContainer(), e, data);
	}

	public <T> JsonContainer<T> setErrorMessage(JsonContainer jsonContainer, Exception e, T data) {
		// 将exception转换为BusinessException
		BusinessException businessException = BusinessException.convert(BusinessCode.C9999, e);
		jsonContainer.set(businessException);
		jsonContainer.setData(data);
		log.error(businessException.getMessage(), businessException);
		// 线程保存错误
		// TODO TCApiInteractiveDetail detail = TCApiInteractiveDetail.get();
		// detail.setErrorMessage(ExceptionUtils.getStackTrace(e));
		return jsonContainer;
	}
}
