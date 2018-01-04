package com.sbm.module.template;

import com.sbm.module.template.api.use.biz.IUseService;
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

	@Test
	public void contextLoads() {
	}

}
