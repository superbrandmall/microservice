package com.sbm.module.mail.send.test;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.mail.send.biz.ISendService;
import com.sbm.module.templateclient.api.use.domain.Use;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendTest {

	@Autowired
	private ISendService service;

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void send() throws Exception {
		//given
		String recipient = "junkai.zhang@superbrandmall.com	";

		Use vo = new Use();
		vo.setName("mail");

		Map<String,Object> root = new HashMap();
		root.put("message", "张骏恺");
		vo.setModel(root);

		String uri = "http://TEMPLATE/template/api/use/processTemplateIntoString";

		String request = JSON.toJSONString(vo);
		HttpEntity<String> entity = getHttpHeader(request);

		ResponseEntity<JsonContainer> result = restTemplate.exchange(uri, HttpMethod.POST, entity,
				new ParameterizedTypeReference<JsonContainer>() {
				});

		System.out.println(JSON.toJSONString(result));

		String message = "Test message content";

		//when
		//service.send(recipient, message);




	}

	protected HttpEntity<String> getHttpHeader(String request){
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);

		List<MediaType> acceptableMediaTypes = new ArrayList<>();
		acceptableMediaTypes.add(type);
		headers.setAccept(acceptableMediaTypes);

		HttpEntity<String> entity = new HttpEntity<>(request, headers);
		return entity;
	}

}
