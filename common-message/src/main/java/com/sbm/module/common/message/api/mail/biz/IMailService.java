package com.sbm.module.common.message.api.mail.biz;

import com.sbm.module.common.message.api.mail.domain.Mail;

public interface IMailService {

	void send(Mail vo);

	void sendByTemplate(Mail vo);

}
