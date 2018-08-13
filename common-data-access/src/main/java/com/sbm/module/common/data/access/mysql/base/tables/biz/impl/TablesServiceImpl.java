package com.sbm.module.common.data.access.mysql.base.tables.biz.impl;

import com.sbm.module.common.data.access.mysql.base.tables.biz.ITablesService;
import com.sbm.module.common.data.access.mysql.base.tables.domain.Tables;
import com.sbm.module.common.data.access.mysql.base.tables.repository.ITablesRepository;
import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TablesServiceImpl extends DataServiceImpl<Tables, Integer> implements ITablesService {

	@Autowired
	private ITablesRepository repository;


}
