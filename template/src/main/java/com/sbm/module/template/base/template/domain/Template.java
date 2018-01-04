package com.sbm.module.template.base.template.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "T_C_TEMPLATE")
@Data
public class Template extends DataEntity {

	private String code;

	private String templateName;

	private String path;

	private String name;

	private String remark;

	@Column(columnDefinition = "mediumtext")
	private String content;

}
