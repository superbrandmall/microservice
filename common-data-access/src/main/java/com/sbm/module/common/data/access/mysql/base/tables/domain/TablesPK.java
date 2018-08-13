package com.sbm.module.common.data.access.mysql.base.tables.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class TablesPK implements Serializable {

	private String tableSchema;

	private String tableName;

}
