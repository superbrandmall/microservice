package com.sbm.module.common.authorization.base.role.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_c_role")
@Data
public class TCRole extends DataEntity {

	private String code;

	private String role;

	private String roleName;

	private String remark;

}
