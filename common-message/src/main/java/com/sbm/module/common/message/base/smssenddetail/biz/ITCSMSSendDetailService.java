package com.sbm.module.common.message.base.smssenddetail.biz;

import com.sbm.module.common.data.biz.IDataService;
import com.sbm.module.common.message.base.smssenddetail.domain.TCSMSSendDetail;

public interface ITCSMSSendDetailService extends IDataService<TCSMSSendDetail, Integer> {

	TCSMSSendDetail newInstance();

}
