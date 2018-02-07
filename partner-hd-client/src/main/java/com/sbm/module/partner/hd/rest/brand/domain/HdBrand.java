package com.sbm.module.partner.hd.rest.brand.domain;

import com.sbm.module.partner.hd.rest.base.domain.HdBizType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class HdBrand {

	private String uuid;

	private String code;

	private String name;

	private String foreignName;

	private String state;

	@ApiModelProperty(value = "城市")
	private String local;

	@ApiModelProperty(value = "业态")
	private HdBizType bizType = new HdBizType();

	private HdBrandProperties properties = new HdBrandProperties();

}
