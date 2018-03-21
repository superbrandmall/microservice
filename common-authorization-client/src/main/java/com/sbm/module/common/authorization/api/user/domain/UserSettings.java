package com.sbm.module.common.authorization.api.user.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserSettings {

	@ApiModelProperty(value = "姓名")
	private String name;

	@ApiModelProperty(value = "证件")
	private String idCard;

	@ApiModelProperty(value = "证件类型")
	private Integer idCardType;

	@ApiModelProperty(value = "证件是否验证")
	private Integer idCardVerified;

	@ApiModelProperty(value = "证件正面")
	private String idCardFront;

	@ApiModelProperty(value = "证件背面")
	private String idCardBack;

	@ApiModelProperty(value = "语言")
	private Integer lang;

	@ApiModelProperty(value = "境内境外")
	private Integer international;

	public UserSettings(String name, String idCard, Integer idCardType, Integer idCardVerified, String idCardFront, String idCardBack, Integer lang, Integer international) {
		this.name = name;
		this.idCard = idCard;
		this.idCardType = idCardType;
		this.idCardVerified = idCardVerified;
		this.idCardFront = idCardFront;
		this.idCardBack = idCardBack;
		this.lang = lang;
		this.international = international;
	}

	public UserSettings() {

	}
}
