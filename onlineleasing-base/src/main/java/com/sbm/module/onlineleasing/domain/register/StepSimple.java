package com.sbm.module.onlineleasing.domain.register;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StepSimple extends StepOne {

	@ApiModelProperty(value = "用户姓名")
	private String userName;

	@ApiModelProperty(value = "公司名称")
	private String merchantName;

	@ApiModelProperty(value = "品牌名称")
	private String brandName;

	@ApiModelProperty(value = "业态")
	private String modality;

	@ApiModelProperty(value = "官网")
	private String website;

	@ApiModelProperty(value = "文件")
	private String file;

}
