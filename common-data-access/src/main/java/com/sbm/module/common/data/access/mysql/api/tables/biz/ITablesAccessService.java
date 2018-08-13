package com.sbm.module.common.data.access.mysql.api.tables.biz;

import com.sbm.module.common.data.access.mysql.api.tables.domain.TablesAccess;
import com.sbm.module.common.data.access.mysql.api.tables.domain.TablesAccessQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITablesAccessService {

	Page<TablesAccess> findAll(TablesAccessQuery query, Pageable pageable);

}
