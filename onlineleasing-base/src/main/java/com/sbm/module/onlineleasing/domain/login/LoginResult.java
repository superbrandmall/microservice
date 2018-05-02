package com.sbm.module.onlineleasing.domain.login;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginResult {

	@ApiModelProperty(value = "用户编号")
	private String code;

	@ApiModelProperty(value = "邮箱")
	private String email;

	@ApiModelProperty(value = "手机")
	private String mobile;

	@ApiModelProperty(value = "商户编号")
	private String merchantCode;

	@ApiModelProperty(value = "商户名称")
	private String merchantName;

	@ApiModelProperty(value = "语言")
	private Integer lang;

	@ApiModelProperty(value = "国籍")
	private Integer international;

	@ApiModelProperty(value = "商户绑定品牌数量")
	private Integer merchantBrandCount;

}
