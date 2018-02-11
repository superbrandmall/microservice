package com.sbm.module.common.util;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.domain.JsonContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestTemplateUtils {

	@Autowired
	private RestTemplate restTemplate;

	/*****************************************************************************************************/
	// 获取方法

	public static final String HTTP = "http://";
	public static final String HTTPS = "https://";

	/**
	 * 默认方法，返回http方法
	 *
	 * @param service
	 * @param method
	 * @return
	 */
	public String getDefaultMethod(String service, String method) {
		return getHttpMethod(service, method);
	}

	/**
	 * http方法
	 *
	 * @param service
	 * @param method
	 * @return
	 */
	public static String getHttpMethod(String service, String method) {
		return getMethod(HTTP, service, method);
	}

	/**
	 * https方法
	 *
	 * @param service
	 * @param method
	 * @return
	 */
	public static String getHttpsMethod(String service, String method) {
		return getMethod(HTTPS, service, method);
	}

	/**
	 * 组织方法
	 *
	 * @param prefix
	 * @param service
	 * @param method
	 * @return
	 */
	public static String getMethod(String prefix, String service, String method) {
		return prefix + service + method;
	}

	/*****************************************************************************************************/
	// 访问对象

	/**
	 * 获取json httpEntity
	 *
	 * @param vo
	 * @return
	 */
	public static HttpEntity<String> getHttpEntityForJSON(Object vo) {
		String request = JSON.toJSONString(vo);
		return getHttpEntityForJSON(request);
	}

	/**
	 * 获取json httpEntity
	 *
	 * @param request
	 * @return
	 */
	public static HttpEntity<String> getHttpEntityForJSON(String request) {
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.APPLICATION_JSON_UTF8;
		headers.setContentType(type);

		List<MediaType> acceptableMediaTypes = new ArrayList<>();
		acceptableMediaTypes.add(type);
		headers.setAccept(acceptableMediaTypes);

		HttpEntity<String> entity = new HttpEntity<>(request, headers);
		return entity;
	}

	/*****************************************************************************************************/
	// 交互
	public <T> ResponseEntity<JsonContainer<T>> exchange(String service, String method, T vo) {
		String uri = getDefaultMethod(service, method);
		HttpEntity<String> entity = getHttpEntityForJSON(vo);
		ResponseEntity<JsonContainer<T>> result = restTemplate.exchange(uri, HttpMethod.POST, entity,
				new ParameterizedTypeReference<JsonContainer<T>>() {
				});
		return result;
	}
}
