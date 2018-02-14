package com.sbm.module.onlineleasing.base.serialcode.repository;

import com.sbm.module.common.data.repository.IDataRepository;
import com.sbm.module.onlineleasing.base.serialcode.domain.TCSerialCode;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "serialcode")
public interface ITCSerialCodeRepository extends IDataRepository<TCSerialCode, Integer> {

	TCSerialCode findOneBySerialGroup(String SerialGroup);

}
