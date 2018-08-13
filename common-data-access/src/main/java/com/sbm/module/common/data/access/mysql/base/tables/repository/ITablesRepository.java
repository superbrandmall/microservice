package com.sbm.module.common.data.access.mysql.base.tables.repository;

import com.sbm.module.common.data.access.mysql.base.tables.domain.Tables;
import com.sbm.module.common.data.repository.IDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "tables")
public interface ITablesRepository extends IDataRepository<Tables, Integer> {


}
