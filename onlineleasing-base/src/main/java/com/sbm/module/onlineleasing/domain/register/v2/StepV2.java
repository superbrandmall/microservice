package com.sbm.module.onlineleasing.domain.register.v2;

import com.sbm.module.onlineleasing.domain.brand.Brand;
import com.sbm.module.onlineleasing.domain.merchant.Merchant;
import com.sbm.module.onlineleasing.domain.register.StepOne;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StepV2 extends StepOne {

	@ApiModelProperty(value = "用户姓名")
	private String userName;

	@ApiModelProperty(value = "商户")
	private Merchant merchant;

	@ApiModelProperty(value = "品牌")
	private Brand brand;

}
