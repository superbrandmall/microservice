package com.sbm.module.common.message.api.sms.biz;

import com.sbm.module.common.message.api.sms.domain.SMS;
import com.sbm.module.common.message.api.sms.domain.SendByTemplate;

public interface ISMSService {

	void send(SMS vo);

	void sendByTemplate(SendByTemplate vo);

}
