package com.sbm.module.common.message.api.mail.biz;

import com.sbm.module.common.message.api.mail.domain.Mail;
import com.sbm.module.common.message.api.mail.domain.SendByTemplate;

public interface IMailService {

	void send(Mail vo);

	void sendByTemplate(SendByTemplate vo);

}
