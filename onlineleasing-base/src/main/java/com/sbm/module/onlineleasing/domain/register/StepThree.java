package com.sbm.module.onlineleasing.domain.register;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class StepThree<T> {

	@ApiModelProperty(value = "品牌")
	@Valid
	@NotNull
	private T brand;

}
