package com.sbm.module.template;

import com.sbm.module.template.api.use.biz.IUseService;
import com.sbm.module.templateclient.api.use.domain.Use;
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
public class TemplateApplicationTests {

	@Autowired
	private IUseService useService;

	@Test
	public void contextLoads() {
		Use vo = new Use();
		vo.setName("mail");

		Map<String,Object> root = new HashMap();
		root.put("message", "张骏恺");
		vo.setModel(root);

		useService.processTemplateIntoString(vo);
		System.out.println(vo.getResult());

	}

}
