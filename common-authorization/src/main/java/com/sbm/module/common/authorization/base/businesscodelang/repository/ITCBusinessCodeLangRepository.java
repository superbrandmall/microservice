package com.sbm.module.common.authorization.base.businesscodelang.repository;

import com.sbm.module.common.authorization.base.businesscodelang.domain.TCBusinessCodeLang;
import com.sbm.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "businesscodelang")
public interface ITCBusinessCodeLangRepository extends IDataRepository<TCBusinessCodeLang, Integer> {

	List<TCBusinessCodeLang> findAllByCode(String code);

	TCBusinessCodeLang findOneByCodeAndLang(String code, String lang);

}
