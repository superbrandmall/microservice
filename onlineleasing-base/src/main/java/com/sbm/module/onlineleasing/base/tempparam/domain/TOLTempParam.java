package com.sbm.module.onlineleasing.base.tempparam.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_ol_temp_param")
@Data
public class TOLTempParam extends DataEntity {

	private String param;

	private Integer key;

	private String value;

}
