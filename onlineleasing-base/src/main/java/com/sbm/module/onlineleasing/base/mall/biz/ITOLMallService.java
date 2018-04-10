package com.sbm.module.onlineleasing.base.mall.biz;

import com.sbm.module.onlineleasing.base.mall.domain.TOLMall;
import com.sbm.module.onlineleasing.data.biz.IOLDataService;

import java.util.List;

public interface ITOLMallService extends IOLDataService<TOLMall, Integer> {

	TOLMall newInstance();

	TOLMall findOneByHdUuid(String hdUuid);

	TOLMall findOneByHdCodeAndHdState(String hdCode, String hdState);

	List<TOLMall> findAllByHdState(String hdState);

	List<TOLMall> findAllByHdStateOrderByPosition(String hdState);
}
