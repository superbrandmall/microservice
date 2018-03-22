package com.sbm.module.onlineleasing.base.modality.biz;

import com.sbm.module.onlineleasing.base.modality.domain.TOLModality;
import com.sbm.module.onlineleasing.data.biz.IOLDataService;

import java.util.List;

public interface ITOLModalityService extends IOLDataService<TOLModality, Integer> {

	TOLModality findOneByHdUuid(String hdUuid);

	List<TOLModality> findAllByLvAndCodeStartingWith(String lv, String code);
}
