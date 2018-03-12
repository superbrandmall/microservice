package com.sbm.module.common.authorization.base.rolemethod.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_c_role_method")
@Data
public class TCRoleMethod extends DataEntity {

	private String roleCode;

	private String methodCode;


}
