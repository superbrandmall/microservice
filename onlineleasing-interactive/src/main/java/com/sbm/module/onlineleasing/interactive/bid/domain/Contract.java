package com.sbm.module.onlineleasing.interactive.bid.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Contract {

	@ApiModelProperty(value = "保证金单号,审批通过则该项为必填")
	private String depositBillNumber;

	@ApiModelProperty(value = "文件ID,审批通过则该项为必填")
	private String fileId;
}
