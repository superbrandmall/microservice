package com.sbm.module.onlineleasing.interactive.bid.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class EffectResult {

	@ApiModelProperty(value = "出价")
	@Valid
	@NotNull
	private Effect bid;

}
