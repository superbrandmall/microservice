package com.sbm.module.partner.hd.rest.base.config;

import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class HdRestConfig {

	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor("yourapp-name", "yourapp-password");
	}

	@Bean
	public Request.Options feignOptions() {
		return new Request.Options(60 * 1000, 300 * 1000);
	}

}
