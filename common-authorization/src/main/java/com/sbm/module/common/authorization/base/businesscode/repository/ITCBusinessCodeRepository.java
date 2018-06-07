package com.sbm.module.common.authorization.base.businesscode.repository;

import com.sbm.module.common.authorization.base.businesscode.domain.TCBusinessCode;
import com.sbm.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "businesscode")
public interface ITCBusinessCodeRepository extends IDataRepository<TCBusinessCode, Integer> {

	TCBusinessCode findOneByCode(String code);

	TCBusinessCode findOneByBusinessClazzAndBusinessCode(String businessClazz, String businessCode);
}
