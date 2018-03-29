package com.sbm.module.onlineleasing.customer.brand.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class BindingBrand {

	@ApiModelProperty(value = "品牌")
	@Valid
	@NotNull
	private Brand brand;

	@ApiModelProperty(value = "商户编号")
	@NotBlank
	private String merchantCode;

	@ApiModelProperty(value = "品牌授权书")
	@NotBlank
	private String brandAuthor;
}
