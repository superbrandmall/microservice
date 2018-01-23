package com.sbm.module.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class ApiInteractiveFilter extends ZuulFilter {

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		//log.info("send {} request to {}, Authorization: {}", request.getMethod(), request.getRequestURL().toString(), request.getHeader("Authorization"));

		HttpServletResponse response = ctx.getResponse();
		//log.info("response: {}", response.getHeaderNames());

		for (String name : response.getHeaderNames()) {
			//log.info("{}: {}", name, response.getHeader(name));
		}
		return null;
	}
}
