package com.sbm.module.onlineleasing.interactive.bid.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Effect {

	@ApiModelProperty(value = "意向合同编号")
	@NotNull
	private String billNumber;

	@ApiModelProperty(value = "是否生效:1-生效,2-不生效,其他返回异常")
	@NotNull
	private Integer isEffect;

}
