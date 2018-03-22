package com.sbm.module.onlineleasing.domain.base.info;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ModalityProportionDetail {

	@ApiModelProperty(value = "业态编号")
	private String code;

	@ApiModelProperty(value = "数量")
	private Long count;

	@ApiModelProperty(value = "占比")
	private BigDecimal percentage;

	public ModalityProportionDetail(String code, Long count, BigDecimal percentage) {
		this.code = code;
		this.count = count;
		this.percentage = percentage;
	}

	public ModalityProportionDetail() {

	}
}
