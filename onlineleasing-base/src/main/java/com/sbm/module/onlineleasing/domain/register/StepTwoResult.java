package com.sbm.module.onlineleasing.domain.register;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StepTwoResult {

	@ApiModelProperty(value = "用户编号")
	private String userCode;

	@ApiModelProperty(value = "商户编号")
	private String merchantCode;

	@ApiModelProperty(value = "商户绑定品牌数量")
	private Integer merchantBrandCount;

	public StepTwoResult(String userCode, String merchantCode) {
		this.userCode = userCode;
		this.merchantCode = merchantCode;
	}

	public StepTwoResult() {

	}
}
