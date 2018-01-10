package com.sbm.module.mail.api.send.biz.impl;

import com.sbm.module.mail.api.send.biz.ISendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class SendServiceImpl implements ISendService{

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	@Async
	public void send(String recipient, String message) {
		prepareAndSend(recipient, message);
	}

	protected void prepareAndSend(String recipient, String message) {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("295322187@qq.com");
			messageHelper.setTo(recipient);
			messageHelper.setSubject("Sample mail subject");
			messageHelper.setText(message);
		};
		try {
			javaMailSender.send(messagePreparator);
		} catch (MailException e) {

		}
	}

}
