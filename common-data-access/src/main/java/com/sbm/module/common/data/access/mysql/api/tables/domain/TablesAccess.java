package com.sbm.module.common.data.access.mysql.api.tables.domain;

import lombok.Data;

@Data
public class TablesAccess {

	private String tableSchema;

	private String tableName;

	private String tableType;

	private String tableComment;

	public TablesAccess() {
	}

	public TablesAccess(String tableSchema, String tableName, String tableType, String tableComment) {

		this.tableSchema = tableSchema;
		this.tableName = tableName;
		this.tableType = tableType;
		this.tableComment = tableComment;
	}
}
