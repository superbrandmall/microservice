package com.sbm.module.onlineleasing.interactive.bid.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class ApproveResult {

	@ApiModelProperty(value = "出价")
	@Valid
	@NotNull
	private Approve bid;

	@ApiModelProperty(value = "出价合同")
	private Contract bidContract;

}
