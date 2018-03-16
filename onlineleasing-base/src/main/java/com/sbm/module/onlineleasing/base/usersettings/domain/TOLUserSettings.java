package com.sbm.module.onlineleasing.base.usersettings.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_ol_user_settings")
@Data
public class TOLUserSettings extends DataEntity {

	private String code;

	private String merchantCode;

	private String name;

	private String idCard;

	private Integer idCardType;

	private Integer idCardVerified;

	@Column(columnDefinition = "text")
	private String idCardFront;

	@Column(columnDefinition = "text")
	private String idCardBack;

}
