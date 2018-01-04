package com.sbm.module.template;

import com.alibaba.fastjson.JSON;
import com.sbm.module.template.api.use.biz.IUseService;
import com.sbm.module.template.base.template.biz.ITemplateService;
import com.sbm.module.template.base.template.domain.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateApplicationTests {

	@Autowired
	private IUseService useService;

	@Autowired
	private ITemplateService templateService;

	@Test
	public void contextLoads() {

		Iterable<Template> templates = templateService.findAll();

		System.out.println(JSON.toJSONString(templates));

	}

}
