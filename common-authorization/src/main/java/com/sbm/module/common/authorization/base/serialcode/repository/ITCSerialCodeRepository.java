package com.sbm.module.common.authorization.base.serialcode.repository;

import com.sbm.module.common.authorization.base.serialcode.domain.TCSerialCode;
import com.sbm.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "serialcode")
public interface ITCSerialCodeRepository extends IDataRepository<TCSerialCode, Integer> {

	TCSerialCode findOneBySerialGroup(String SerialGroup);

}
