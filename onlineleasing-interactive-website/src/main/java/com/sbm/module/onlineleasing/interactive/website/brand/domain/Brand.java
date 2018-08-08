package com.sbm.module.onlineleasing.interactive.website.brand.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Brand {

	@ApiModelProperty(value = "编号")
	private String code;

	@ApiModelProperty(value = "Brand名称")
	private String brandName;

	@ApiModelProperty(value = "Brand名称（英文）")
	private String brandNameEng;

	@ApiModelProperty(value = "业态编号")
	private String modality;

	@ApiModelProperty(value = "状态")
	private Integer state;

	@ApiModelProperty(value = "系统状态")
	private String hdState;

	@ApiModelProperty(value = "系统编号")
	private String hdCode;

	public Brand(String code, String brandName, String brandNameEng, String modality, Integer state, String hdState, String hdCode) {
		this.code = code;
		this.brandName = brandName;
		this.brandNameEng = brandNameEng;
		this.modality = modality;
		this.state = state;
		this.hdState = hdState;
		this.hdCode = hdCode;
	}

	public Brand() {

	}
}
