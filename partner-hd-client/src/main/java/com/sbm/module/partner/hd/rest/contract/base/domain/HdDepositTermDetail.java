package com.sbm.module.partner.hd.rest.contract.base.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class HdDepositTermDetail {

	@ApiModelProperty(value = "金额")
	private BigDecimal value;

	public HdDepositTermDetail(BigDecimal value) {
		this.value = value;
	}

	public HdDepositTermDetail() {

	}
}
