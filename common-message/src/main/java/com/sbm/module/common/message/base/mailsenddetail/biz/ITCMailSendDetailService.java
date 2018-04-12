package com.sbm.module.common.message.base.mailsenddetail.biz;

import com.sbm.module.common.data.biz.IDataService;
import com.sbm.module.common.message.base.mailsenddetail.domain.TCMailSendDetail;

public interface ITCMailSendDetailService extends IDataService<TCMailSendDetail, Integer> {

	TCMailSendDetail newInstance();

}
