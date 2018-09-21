package com.sbm.module.partner.tianyancha.rest.base.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class TianyanchaRequestInterceptor implements RequestInterceptor {

	private final String authorization;

	public TianyanchaRequestInterceptor(String authorization) {
		this.authorization = authorization;
	}

	@Override
	public void apply(RequestTemplate template) {
		template.header("Authorization", new String[]{this.authorization});
	}
}
