package com.sbm.module.onlineleasing.customer.verify.biz.impl;

import com.sbm.module.common.authorization.api.verificationcode.client.IVerificationCodeClient;
import com.sbm.module.common.authorization.api.verificationcode.domain.VerificationCode;
import com.sbm.module.common.authorization.api.verificationcode.domain.VerificationCodeCheck;
import com.sbm.module.common.authorization.api.verificationcode.domain.VerificationCodeSetting;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.message.api.mail.client.IMailClient;
import com.sbm.module.common.message.api.sms.client.ISMSClient;
import com.sbm.module.onlineleasing.customer.verify.biz.IVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class VerifyServiceImpl extends CommonServiceImpl implements IVerifyService {

	@Autowired
	private IVerificationCodeClient verificationCodeClient;
	@Autowired
	private IMailClient mailClient;
	@Autowired
	private ISMSClient smsClient;

	private static final String MAIL_KEYWORD = "mail";
	private static final String MAIL_SUBJECT = "Onlineleasing邮件验证码";
	@Value("${verify.mailTemplateCode}")
	private String mailTemplateCode;

	private static final String SMS_KEYWORD = "sms";
	@Value("${verify.smsTemplateCode}")
	private String smsTemplateCode;

	@Override
	public void check(VerificationCodeCheck check) {
		checkJsonContainer(verificationCodeClient.check(check));
	}

	@Override
	public String mail(String mail) {
		// 生成验证码
		VerificationCode verificationCode = checkJsonContainer(verificationCodeClient.generate(new VerificationCodeSetting(MAIL_KEYWORD)));
		// 发送邮件 TODO 后续再考虑总线
		Map<String, String> model = new HashMap<>();
		model.put("verificationcode", verificationCode.getCode());
		checkJsonContainer(mailClient.sendByTemplate(new com.sbm.module.common.message.api.mail.domain.SendByTemplate(mail, MAIL_SUBJECT, null, new Date(), mailTemplateCode, model)));
		// 返回键
		return verificationCode.getKey();
	}

	@Override
	public String sms(String mobile) {
		// 生成验证码
		VerificationCode verificationCode = checkJsonContainer(verificationCodeClient.generate(new VerificationCodeSetting(SMS_KEYWORD)));
		// 发送短信 TODO 后续再考虑总线
		Map<String, String> model = new HashMap<>();
		model.put("verificationcode", verificationCode.getCode());
		checkJsonContainer(smsClient.sendByTemplate(new com.sbm.module.common.message.api.sms.domain.SendByTemplate(mobile, null, new Date(), smsTemplateCode, model)));
		// 返回键
		return verificationCode.getKey();
	}
}
