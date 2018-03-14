package com.sbm.module.common.authorization.base.user.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "t_c_user")
@Data
public class TCUser extends DataEntity {

	private String code;

	private String email;

	private String mobile;

	private String password;

	@Column(columnDefinition = "timestamp")
	private Date lastLogin;

	private Integer emailVerified;

	private Integer mobileVerified;


}
