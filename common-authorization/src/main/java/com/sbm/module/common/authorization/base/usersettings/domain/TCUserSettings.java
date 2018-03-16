package com.sbm.module.common.authorization.base.usersettings.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_c_user_settings")
@Data
public class TCUserSettings extends DataEntity {

	private String code;

	private String name;

	private String idCard;

	private Integer idCardType;

	private Integer idCardVerified;

	@Column(columnDefinition = "text")
	private String idCardFront;

	@Column(columnDefinition = "text")
	private String idCardBack;

}
