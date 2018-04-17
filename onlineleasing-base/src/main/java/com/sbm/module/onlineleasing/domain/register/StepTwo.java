package com.sbm.module.onlineleasing.domain.register;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StepTwo {

	@ApiModelProperty(value = "用户编号")
	private String userCode;

	@ApiModelProperty(value = "用户名称")
	private String userName;

	@ApiModelProperty(value = "证件号")
	private String idCard;

	@ApiModelProperty(value = "证件类型")
	private Integer idCardType;

	@ApiModelProperty(value = "商户编号")
	private String merchantCode;

	@ApiModelProperty(value = "商户类型")
	private Integer type;

	@ApiModelProperty(value = "营业执照")
	private String businessLicense;

}
