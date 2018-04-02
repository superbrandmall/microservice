package com.sbm.module.onlineleasing.domain.brand;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class ExistingBrand {

	@ApiModelProperty(value = "品牌编号")
	@NotBlank
	private String brandCode;

	@ApiModelProperty(value = "商户编号")
	@NotBlank
	private String merchantCode;

	@ApiModelProperty(value = "品牌授权书")
	@NotBlank
	private String brandAuthor;
}
