package com.sbm.module.common.data.access.mysql.base.tables.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(catalog = "information_schema", name = "tables")
@IdClass(TablesPK.class)
@Data
public class Tables {

	@Id
	private String tableSchema;

	@Id
	private String tableName;

	private String tableType;

	private String tableComment;

}
