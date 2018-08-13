package com.sbm.module.common.data.access.mysql.api.tables.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.data.access.mysql.api.tables.biz.ITablesAccessService;
import com.sbm.module.common.data.access.mysql.api.tables.domain.TablesAccess;
import com.sbm.module.common.data.access.mysql.api.tables.domain.TablesAccessQuery;
import com.sbm.module.common.data.access.mysql.base.tables.biz.ITablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TablesAccessServiceImpl extends CommonServiceImpl implements ITablesAccessService {

	@Autowired
	private ITablesService tablesService;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<TablesAccess> findAll(TablesAccessQuery query, Pageable pageable) {
		return tablesService.findAll(query.findAll(), pageable).map(e -> new TablesAccess(e.getTableSchema(), e.getTableName(),
				e.getTableType(), e.getTableComment()));
	}

}
