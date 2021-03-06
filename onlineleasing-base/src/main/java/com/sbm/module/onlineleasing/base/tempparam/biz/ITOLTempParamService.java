package com.sbm.module.onlineleasing.base.tempparam.biz;

import com.sbm.module.common.data.biz.IDataService;
import com.sbm.module.onlineleasing.base.tempparam.domain.TOLTempParam;

public interface ITOLTempParamService extends IDataService<TOLTempParam, Integer> {

	TOLTempParam findOneByParamAndValue(String param, String value);

	Integer findKeyByParamAndValue(String param, String value, String hdUuid);

}
