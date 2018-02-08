package com.sbm.module.partner.hd.rest.contract.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class HdContractNonStandardResultDetail {

	@ApiModelProperty(value = "OL合同id")
	private String contractId;

	@ApiModelProperty(value = "文件id")
	private String fileId;

	@ApiModelProperty(value = "单据号")
	private String billNumber;

}
