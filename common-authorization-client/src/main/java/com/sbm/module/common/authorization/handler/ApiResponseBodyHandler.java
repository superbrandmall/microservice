package com.sbm.module.common.authorization.handler;


import com.sbm.module.common.authorization.api.businesscode.biz.IBusinessCodeRegisterService;
import com.sbm.module.common.authorization.api.businesscode.client.IBusinessCodeClient;
import com.sbm.module.common.authorization.api.businesscode.domain.BusinessCode;
import com.sbm.module.common.authorization.api.businesscode.domain.BusinessCodeLang;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.provider.SpringContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ApiResponseBodyHandler extends CommonServiceImpl implements ResponseBodyAdvice {

	@Autowired
	private SpringContextProvider provider;

	@Autowired
	private IBusinessCodeClient client;

	@Override
	public boolean supports(MethodParameter methodParameter, Class aClass) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
		if (o instanceof JsonContainer) {
			ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) serverHttpRequest;
			HttpServletRequest request = servletServerHttpRequest.getServletRequest();
			// 获取语言
			String lang = request.getHeader("Lang");
			// 获取返回对象
			JsonContainer jsonContainer = (JsonContainer) o;
			// 客户消息
			String customerMessage = getCustomerMessageByLang(jsonContainer.getClazz(), jsonContainer.getCode(), lang);
			if (StringUtils.isNotBlank(customerMessage)) {
				jsonContainer.setCustomerMessage(customerMessage);
			}
		}
		return o;
	}

	private String getCustomerMessageByLang(String businessClazz, String businessCode, String lang) {
		// 任意参数为空，返回空
		if (StringUtils.isBlank(businessClazz) || StringUtils.isBlank(businessCode) || StringUtils.isBlank(lang)) {
			return null;
		}
		BusinessCode vo;
		IBusinessCodeRegisterService service = null;
		// 判断本地是否有IBusinessCodeRegisterService实现
		try {
			service = provider.getBean(IBusinessCodeRegisterService.class);
		} catch (Exception ex) {

		}
		// 有就用
		if (null != service) {
			vo = service.findOneByBusinessClazzAndBusinessCode(businessClazz, businessCode);
		}
		// 没有就调服务
		else {
			vo = checkJsonContainer(client.findOneByBusinessClazzAndBusinessCode(businessClazz, businessCode));
		}
		// 没有查询到，或者语言为空，或者语言列表为空，返回空
		if (null == vo || null == vo.getBusinessCodeLangs() || vo.getBusinessCodeLangs().isEmpty()) {
			return null;
		}
		// 查询语言满足的
		BusinessCodeLang businessCodeLang = vo.getBusinessCodeLangs().stream().filter(e -> lang.equalsIgnoreCase(e.getLang())).findFirst().orElse(null);
		// 没有查询到，返回空
		if (null == businessCodeLang) {
			return null;
		}
		// 查询到，返回对应客户信息
		return businessCodeLang.getCustomerMessage();
	}
}
