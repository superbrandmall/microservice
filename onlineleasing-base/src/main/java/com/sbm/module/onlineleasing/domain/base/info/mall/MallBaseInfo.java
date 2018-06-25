package com.sbm.module.onlineleasing.domain.base.info.mall;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MallBaseInfo {

	@ApiModelProperty(value = "商场编号")
	private String mallCode;

	@ApiModelProperty(value = "商场名称")
	private String mallName;

	@ApiModelProperty(value = "商场名称（英文）")
	private String mallNameEng;

	public MallBaseInfo() {
	}

	public MallBaseInfo(String mallCode, String mallName, String mallNameEng) {

		this.mallCode = mallCode;
		this.mallName = mallName;
		this.mallNameEng = mallNameEng;
	}
}
