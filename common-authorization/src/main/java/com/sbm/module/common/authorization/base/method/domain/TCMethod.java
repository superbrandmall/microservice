package com.sbm.module.common.authorization.base.method.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_c_method")
@Data
public class TCMethod extends DataEntity {

	private String code;

	private String applicationName;

	private String method;

	private String pattern;

	private String remark;

}
