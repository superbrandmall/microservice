package com.sbm.module.mail.send.test;

import com.alibaba.fastjson.JSON;
import com.sbm.module.mail.send.biz.ISendService;
import freemarker.cache.StringTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendTest {

	@Autowired
	private ISendService service;

	@Autowired
	private Configuration configuration;

	@Test
	public void send() throws Exception {
//		//given
//		String recipient = "junkai.zhang@superbrandmall.com	";
//		String message = "Test message content";
//		//when
//		service.send(recipient, message);

//		String content = "你的名字${name}";
//		StringTemplateLoader stringLoader = new StringTemplateLoader();
//		stringLoader.putTemplate("contract", content);
//		configuration.setTemplateLoader(stringLoader);
//		Template template = configuration.getTemplate("contract");
//
//		Map<String,Object> root = new HashMap();
//		root.put("name", "张骏恺");
//
//		String str = FreeMarkerTemplateUtils.processTemplateIntoString(template, root);
//		System.out.println(str);

	}



}
