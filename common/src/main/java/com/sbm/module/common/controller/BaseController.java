package com.sbm.module.common.controller;

import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.exception.BusinessCode;
import com.sbm.module.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseController {

	/************** 创建json容器 ***************/
	public <T> JsonContainer getJsonContainer() {
		return new JsonContainer<T>();
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
		return setErrorMessage(e, null);
	}

	public <T> JsonContainer<T> setErrorMessage(Exception e, T data) {
		return setErrorMessage(getJsonContainer(), e, data);
	}

	public <T> JsonContainer<T> setErrorMessage(JsonContainer<T> jsonContainer, Exception e, T data) {
		// 将exception转换为BusinessException
		BusinessException businessException = BusinessException.convert(BusinessCode.C9999, e, data, new Object[]{e.getMessage()});
		jsonContainer.set(businessException);
		if (null != data) {
			jsonContainer.setData(data);
		}
		// 未知异常和空指针打出错误堆栈
		if (BusinessCode.C9999.getCode().equals(businessException.getCode()) || BusinessCode.C9998.getCode().equals(businessException.getCode())) {
			log.error(businessException.getLogMessage(), businessException);
		}
		// 其他异常不打错误堆栈
		else {
			log.error(businessException.getLogMessage());
		}
		return jsonContainer;
	}
}
