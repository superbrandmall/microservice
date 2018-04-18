package com.sbm.module.onlineleasing.domain.register;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StepThreeResult {

	@ApiModelProperty(value = "商户绑定品牌数量")
	private Integer merchantBrandCount;

	public StepThreeResult(Integer merchantBrandCount) {
		this.merchantBrandCount = merchantBrandCount;
	}

	public StepThreeResult() {

	}
}
