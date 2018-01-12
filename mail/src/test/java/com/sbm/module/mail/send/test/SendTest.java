package com.sbm.module.mail.send.test;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.mail.api.send.biz.ISendService;
import com.sbm.module.template.api.use.client.IUseClient;
import com.sbm.module.template.api.use.domain.Use;
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

		System.out.println(Thread.currentThread().getName());
		System.out.println(Thread.currentThread().getId());

		Use vo = new Use();
		vo.setName("CMAILTEMP170801000001");

		Map<String,Object> root = new HashMap();
		root.put("verificationcode", "666666");
		vo.setModel(root);

		JsonContainer<Use> result = useClient.processTemplateIntoString(vo);
		System.out.println(JSON.toJSONString(result));

	}

}
