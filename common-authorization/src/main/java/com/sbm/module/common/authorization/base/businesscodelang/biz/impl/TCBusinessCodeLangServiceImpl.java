package com.sbm.module.common.authorization.base.businesscodelang.biz.impl;

import com.sbm.module.common.authorization.base.businesscodelang.biz.ITCBusinessCodeLangService;
import com.sbm.module.common.authorization.base.businesscodelang.domain.TCBusinessCodeLang;
import com.sbm.module.common.authorization.base.businesscodelang.repository.ITCBusinessCodeLangRepository;
import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TCBusinessCodeLangServiceImpl extends DataServiceImpl<TCBusinessCodeLang, Integer> implements ITCBusinessCodeLangService {

	@Autowired
	private ITCBusinessCodeLangRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TCBusinessCodeLang> findAllByCode(String code) {
		return repository.findAllByCode(code);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TCBusinessCodeLang findOneByCodeAndLang(String code, String lang) {
		return repository.findOneByCodeAndLang(code, lang);
	}
}
