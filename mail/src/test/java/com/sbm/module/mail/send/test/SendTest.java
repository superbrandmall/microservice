package com.sbm.module.mail.send.test;

import com.sbm.module.mail.send.biz.ISendService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendTest {

	@Autowired
	private ISendService service;

	@Test
	public void shouldSendMail() throws Exception {
		//given
		String recipient = "junkai.zhang@superbrandmall.com	";
		String message = "Test message content";
		//when
		service.send(recipient, message);
	}
}
