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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailServiceImpl extends CommonServiceImpl implements IMailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private ITemplateService templateService;

	@Autowired
	private ITCMailSendDetailService mailSendDetailService;

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
		MimeMessagePreparator preparator = e -> {
			MimeMessageHelper helper = new MimeMessageHelper(e);
			helper.setFrom(from);
			helper.setTo(vo.getTo());
			helper.setSubject(vo.getSubject());
			helper.setText(vo.getMessage(), true);
		};
		javaMailSender.send(preparator);
	}


	private TCMailSendDetail convert(Mail vo) {
		TCMailSendDetail po = mailSendDetailService.newInstance();
		po.setSentFrom(from);
		po.setSentTo(vo.getTo());
		// cc暂无
		po.setSentDate(vo.getDate());
		po.setSubject(vo.getSubject());
		po.setHtml(vo.getMessage());
		return po;
	}

}
