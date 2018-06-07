package com.sbm.module.common.authorization.handler;


import com.alibaba.fastjson.JSON;
import com.sbm.module.common.domain.JsonContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ApiResponseBodyHandler implements ResponseBodyAdvice {

	@Override
	public boolean supports(MethodParameter methodParameter, Class aClass) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
		if (o instanceof JsonContainer) {
			ServletServerHttpRequest sshr = (ServletServerHttpRequest) serverHttpRequest;
			HttpServletRequest request = sshr.getServletRequest();
			String lang = request.getHeader("lang");
			//System.out.println("Lang :" + lang);
			JsonContainer jsonContainer = (JsonContainer) o;
			//System.out.println(JSON.toJSONString(jsonContainer));
			log.info("Lang:" + lang + JSON.toJSONString(jsonContainer));
		}
		return o;
	}
}
