package com.sbm.module.common.authorization.base.businesscode.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_c_business_code")
@Data
public class TCBusinessCode extends DataEntity {

	private String code;

	private String businessClazz;

	private String businessCode;

	private String message;

	private String customerMessage;

}
