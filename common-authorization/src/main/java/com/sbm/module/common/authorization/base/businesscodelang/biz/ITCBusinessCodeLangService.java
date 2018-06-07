package com.sbm.module.common.authorization.base.businesscodelang.biz;

import com.sbm.module.common.authorization.base.businesscodelang.domain.TCBusinessCodeLang;
import com.sbm.module.common.data.biz.IDataService;

import java.util.List;

public interface ITCBusinessCodeLangService extends IDataService<TCBusinessCodeLang, Integer> {

	List<TCBusinessCodeLang> findAllByCode(String code);

	TCBusinessCodeLang findOneByCodeAndLang(String code, String lang);

}
