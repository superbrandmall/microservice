package com.sbm.module.partner.tianyancha.rest.base.config;

import feign.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

//@Configuration
public class TianyanchaConfig {

	@Value("${tianyancha.token}")
	private String token;

	@Bean
	public TianyanchaRequestInterceptor basicAuthRequestInterceptor() {
		return new TianyanchaRequestInterceptor(token);
	}

	@Bean
	public Request.Options feignOptions() {
		return new Request.Options(60 * 1000, 300 * 1000);
	}

}
