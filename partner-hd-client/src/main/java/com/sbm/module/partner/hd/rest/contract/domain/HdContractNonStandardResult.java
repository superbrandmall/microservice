package com.sbm.module.partner.hd.rest.contract.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class HdContractNonStandardResult {

	@ApiModelProperty(value = "编号")
	private String billNumber;

	@ApiModelProperty(value = "明细")
	private List<HdContractNonStandardResultDetail> details;

}
