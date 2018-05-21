package com.sbm.module.common.message.api.mail.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.message.api.mail.biz.IMailService;
import com.sbm.module.common.message.api.mail.domain.Mail;
import com.sbm.module.common.message.api.mail.domain.SendByTemplate;
import com.sbm.module.common.message.api.template.biz.ITemplateService;
import com.sbm.module.common.message.api.template.domain.Template;
import com.sbm.module.common.message.base.mailsenddetail.biz.ITCMailSendDetailService;
import com.sbm.module.common.message.base.mailsenddetail.constant.MailConstant;
import com.sbm.module.common.message.base.mailsenddetail.domain.TCMailSendDetail;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.BodyType;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.MessageBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Date;

@Service
@Slf4j
public class MailServiceImpl extends CommonServiceImpl implements IMailService {

//	@Autowired
//	private JavaMailSender javaMailSender;

	@Autowired
	private ITemplateService templateService;

	@Autowired
	private ITCMailSendDetailService mailSendDetailService;

	@Value("${spring.mail.host}")
	private String host;
	@Value("${spring.mail.username}")
	private String username;
	@Value("${spring.mail.password}")
	private String password;

	@Value("${spring.mail.username}")
	private String from;

	private static final String ERROR_MESSAGE = "邮件发送失败";

	@Async
	@Override
	public void send(Mail vo) {
		TCMailSendDetail po = convert(vo);
		try {
			// 发送
			prepareAndSend(vo);
			po.setHtml(vo.getMessage());
			po.setType(MailConstant.SUCCESS);
		} catch (Exception ex) {
			log.error(ERROR_MESSAGE, ex);
			po.setType(MailConstant.FAILURE);
		}
		mailSendDetailService.save(po);
	}

	@Async
	@Override
	public void sendByTemplate(SendByTemplate vo) {
		TCMailSendDetail po = convert(vo);
		po.setTemplateCode(vo.getCode());
		try {
			// 模板结果
			vo.setMessage(templateService.processTemplateIntoString(new Template(vo.getCode(), vo.getModel())));
			// 发送
			prepareAndSend(vo);
			po.setHtml(vo.getMessage());
			po.setType(MailConstant.SUCCESS);
		} catch (Exception ex) {
			log.error(ERROR_MESSAGE, ex);
			po.setType(MailConstant.FAILURE);
		}
		mailSendDetailService.save(po);
	}

	/**
	 * 准备并发送邮件
	 *
	 * @param vo
	 */
	protected void prepareAndSend(Mail vo) {
		exchange(vo);
		// 微软的东西自成一套，不使用默认的javaMailSender，改用微软api
//		MimeMessagePreparator preparator = e -> {
//			MimeMessageHelper helper = new MimeMessageHelper(e);
//			helper.setFrom(from);
//			helper.setTo(vo.getTo());
//			helper.setSubject(vo.getSubject());
//			helper.setText(vo.getMessage(), true);
//		};
//		javaMailSender.send(preparator);
	}


	private TCMailSendDetail convert(Mail vo) {
		TCMailSendDetail po = mailSendDetailService.newInstance();
		po.setSentFrom(from);
		po.setSentTo(vo.getTo());
		// cc暂无
		if (null == vo.getDate()) vo.setDate(new Date());
		po.setSentDate(vo.getDate());
		po.setSubject(vo.getSubject());
		return po;
	}

	@SneakyThrows
	private void exchange(Mail vo) {
		ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2007_SP1);
		ExchangeCredentials credentials = new WebCredentials(username, password);
		service.setCredentials(credentials);
		service.setUrl(new URI("https://" + host + "/EWS/exchange.asmx"));

		EmailMessage msg = new EmailMessage(service);
		msg.setSubject(vo.getSubject());
		MessageBody body = MessageBody.getMessageBodyFromText(vo.getMessage());
		body.setBodyType(BodyType.HTML);
		msg.setBody(body);
		for (String to : vo.getTo().split(",")) {
			msg.getToRecipients().add(to);
		}
		msg.send();
	}

}
