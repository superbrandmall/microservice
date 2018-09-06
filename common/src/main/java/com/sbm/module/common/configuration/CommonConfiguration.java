package com.sbm.module.common.configuration;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.util.HttpsClientUtil;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
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

	@Value("${com.sbm.module.config.swagger2.api.url:https://github.com/superbrandmall/microservice}")
	private String apiUrl;
	@Value("${com.sbm.module.config.swagger2.api.description:superbrandmall的微服务")
	private String description;

	// 忽略SSL
	{
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier((m, n) -> true);
	}

	/****************************************************************************/
	// resttemplate
	@Bean
	public RestTemplate getRestTemplate() throws Exception {
		// 支持https
		return builder.requestFactory(httpComponentsClientHttpRequestFactory()).build();
	}

	public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() throws Exception {
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(
				httpClient());
		httpRequestFactory.setConnectionRequestTimeout(120000);
		httpRequestFactory.setConnectTimeout(120000);
		httpRequestFactory.setReadTimeout(120000);
		return httpRequestFactory;
	}

	public HttpClient httpClient() throws Exception {
		CloseableHttpClient client = HttpsClientUtil.acceptsUntrustedCertsHttpClient();
		return client;
	}

	/****************************************************************************/
	// apidocs
	@Bean
	public Docket api() {
		ParameterBuilder tokenPar = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<>();
		tokenPar.name("Authorization").description("签名").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
		pars.add(tokenPar.build());

		tokenPar = new ParameterBuilder();
		tokenPar.name("Login").description("用户编号").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
		pars.add(tokenPar.build());

		tokenPar = new ParameterBuilder();
		tokenPar.name("Lang").description("语言").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
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
				.description(description)
				.contact(new Contact("张骏恺", apiUrl, "junkai.zhang@superbrandmall.com"))
				.version("1.0")
				.build();
	}


}
