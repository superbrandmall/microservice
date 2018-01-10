package com.sbm.module.common.controller;

import com.sbm.module.common.domain.JsonContainer;

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
		jsonContainer.setCode("C0");
		jsonContainer.setMessage("成功");
		jsonContainer.setData(data);
		return jsonContainer;
	}

}
