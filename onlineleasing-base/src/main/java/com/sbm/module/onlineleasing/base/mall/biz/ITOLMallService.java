package com.sbm.module.onlineleasing.base.mall.biz;

import com.sbm.module.onlineleasing.base.mall.domain.TOLMall;
import com.sbm.module.onlineleasing.data.biz.IOLDataService;

public interface ITOLMallService extends IOLDataService<TOLMall, Integer> {

	TOLMall newInstance();

	TOLMall findOneByHdUuid(String hdUuid);

}
