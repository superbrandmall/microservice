package com.sbm.module.mail.api.send.biz;

import com.sbm.module.mail.api.use.domain.Send;

public interface ISendService {

	void send(Send vo);

	void sendByTemplate(Send vo);

}
