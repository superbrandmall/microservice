package com.sbm.module.onlineleasing.base.tempparam.repository;

import com.sbm.module.common.data.repository.IDataRepository;
import com.sbm.module.onlineleasing.base.tempparam.domain.TOLTempParam;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "tempparam")
public interface ITOLTempParamRepository extends IDataRepository<TOLTempParam, Integer> {

	TOLTempParam findOneByParamAndValue(String param, String value);

}
