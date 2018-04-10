package com.sbm.module.onlineleasing.customer.base.info.mall.biz;

import com.sbm.module.onlineleasing.domain.base.info.mall.MallInfo;
import com.sbm.module.onlineleasing.domain.base.info.mall.MallMinInfo;

import java.util.List;

public interface IMallInfoService {

	MallInfo findOneByMallCode(String mallCode);

	List<MallMinInfo> findAllOrderByPosition();

}
