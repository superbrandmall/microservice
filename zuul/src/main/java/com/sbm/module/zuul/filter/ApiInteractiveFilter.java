package com.sbm.module.zuul.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.sbm.module.common.authorization.api.jsonwebtoken.constant.JSONWebTokenConstant;
import com.sbm.module.common.authorization.api.permission.client.IPermissionClient;
import com.sbm.module.common.authorization.api.permission.domain.Permission;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.exception.BusinessException;
import com.sbm.module.common.util.IPAddressUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class ApiInteractiveFilter extends ZuulFilter {

	@Autowired
	private IPermissionClient client;

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		Permission permission = new Permission();
		// 用户编号
		permission.setLogin(request.getHeader(JSONWebTokenConstant.LOGIN));
		// 口令
		permission.setToken(request.getHeader(JSONWebTokenConstant.AUTHORIZATION));
		// 路径
		permission.setPath(request.getRequestURI());
		// 方法
		permission.setMethod(request.getMethod());
		// IP
		permission.setIp(IPAddressUtil.getIpAddress(request));
		// 校验资源
		JsonContainer<Permission> result = client.valid(permission);
		// 校验返回结果
		try {
			CommonServiceImpl.checkJsonContainer(result);
			// 返回token
			ctx.getResponse().setHeader(JSONWebTokenConstant.LOGIN, result.getData().getLogin());
			ctx.getResponse().setHeader(JSONWebTokenConstant.AUTHORIZATION, result.getData().getToken());
		} catch (BusinessException e) {
			//过滤该请求，不往下级服务去转发请求，到此结束
			ctx.setSendZuulResponse(false);
			ctx.setResponseBody(JSON.toJSONString(result));
			ctx.getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		}
		return null;
	}
}
