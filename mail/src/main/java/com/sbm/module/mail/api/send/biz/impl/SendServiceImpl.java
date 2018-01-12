package com.sbm.module.mail.api.send.biz.impl;

import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.mail.api.send.biz.ISendService;
import com.sbm.module.mail.api.use.domain.Send;
import com.sbm.module.template.api.use.client.IUseClient;
import com.sbm.module.template.api.use.domain.Use;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Autowired
	private IUseClient useClient;

	@Value("${spring.mail.username}")
	private String from;

	@Async
	@Override
	public void send(Send vo) {
		prepareAndSend(vo);
	}

	@Async
	@Override
	public void sendByTemplate(Send vo) {
		// 获取模板
		JsonContainer<Use> result = useClient.processTemplateIntoString(new Use(vo.getName(), vo.getModel()));
		// TODO 判断成功与否，需要做成通用方法
		vo.setMessage(result.getData().getResult());
		prepareAndSend(vo);
	}

	/**
	 * 准备并发送邮件
	 * @param vo
	 */
	protected void prepareAndSend(Send vo) {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom(from);
			messageHelper.setTo(vo.getTo());
			messageHelper.setSubject(vo.getSubject());
			messageHelper.setText(vo.getMessage(), true);
		};
		try {
			javaMailSender.send(messagePreparator);
		} catch (MailException e) {

		}
	}

}
