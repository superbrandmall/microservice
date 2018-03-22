package com.sbm.module.onlineleasing.customer.base.info.mall.biz;

import com.sbm.module.onlineleasing.domain.base.info.mall.MallInfo;

public interface IMallInfoService {

	MallInfo findOneByMallCode(String mallCode);

}
