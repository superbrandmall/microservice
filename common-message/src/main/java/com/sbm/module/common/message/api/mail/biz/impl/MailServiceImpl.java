package com.sbm.module.common.message.api.mail.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.message.api.mail.biz.IMailService;
import com.sbm.module.common.message.api.mail.domain.Mail;
import com.sbm.module.common.message.api.template.biz.ITemplateService;
import com.sbm.module.common.message.api.template.domain.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class MailServiceImpl extends CommonServiceImpl implements IMailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private ITemplateService templateService;

	@Value("${spring.mail.username}")
	private String from;

	@Async
	@Override
	public void send(Mail vo) {
		prepareAndSend(vo);
	}

	@Async
	@Override
	public void sendByTemplate(Mail vo) {
		// 模板结果
		String result = templateService.processTemplateIntoString(new Template(vo.getName(), vo.getModel()));
		vo.setMessage(result);
		prepareAndSend(vo);
	}

	/**
	 * 准备并发送邮件
	 *
	 * @param vo
	 */
	protected void prepareAndSend(Mail vo) {
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
			e.printStackTrace();
		}
	}

}
