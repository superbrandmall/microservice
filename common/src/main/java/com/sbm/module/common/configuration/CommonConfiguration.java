package com.sbm.module.common.configuration;

import com.sbm.module.common.annotation.CreateApiDocs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class CommonConfiguration {

	@Autowired
	private RestTemplateBuilder builder;

	/****************************************************************************/
	// resttemplate
	@Bean
	@LoadBalanced
	public RestTemplate getRestTepmlate() {
		return builder.build();
	}

	/****************************************************************************/
	// apidocs
	@Bean
	public Docket api() {
		ParameterBuilder tokenPar = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<>();
		tokenPar.name("Authorization").description("签名").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
		pars.add(tokenPar.build());
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(CreateApiDocs.class))
				//.apis(RequestHandlerSelectors.basePackage("com.sbm.module"))
				.paths(PathSelectors.any())
				.build()
				// 添加全局参数
				.globalOperationParameters(pars);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("microservice")
				.description("superbrandmall的微服务")
				.contact(new Contact("张骏恺", "https://github.com/superbrandmall/microservice", "junkai.zhang@superbrandmall.com"))
				.version("1.0")
				.build();
	}


}
