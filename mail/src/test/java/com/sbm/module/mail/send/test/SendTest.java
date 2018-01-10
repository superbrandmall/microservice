package com.sbm.module.mail.send.test;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.mail.send.biz.ISendService;
import com.sbm.module.template.client.api.use.client.IUseClient;
import com.sbm.module.template.client.api.use.domain.Use;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendTest {

	@Autowired
	private ISendService service;

	@Autowired
	private IUseClient useClient;

	@Test
	public void send() throws Exception {
		//given
		String recipient = "junkai.zhang@superbrandmall.com	";

		Use vo = new Use();
		vo.setName("CMAILTEMP170801000001");

		Map<String,Object> root = new HashMap();
		root.put("verificationcode", "666666");
		vo.setModel(root);

		JsonContainer<Use> result = useClient.processTemplateIntoString(vo);
		System.out.println(JSON.toJSONString(result));

		String message = "Test message content";

		//when
		//service.send(recipient, message);




	}

}
