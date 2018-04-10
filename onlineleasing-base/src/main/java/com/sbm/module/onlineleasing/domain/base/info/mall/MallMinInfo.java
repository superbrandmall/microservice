package com.sbm.module.onlineleasing.domain.base.info.mall;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MallMinInfo {

	@ApiModelProperty(value = "商场编号")
	private String mallCode;

	@ApiModelProperty(value = "商场名称")
	private String mallName;

	@ApiModelProperty(value = "地址")
	private String location;

	@ApiModelProperty(value = "位置")
	private Integer position;

	@ApiModelProperty(value = "图片")
	private String img;

	public MallMinInfo() {
	}

	public MallMinInfo(String mallCode, String mallName, String location, Integer position, String img) {

		this.mallCode = mallCode;
		this.mallName = mallName;
		this.location = location;
		this.position = position;
		this.img = img;
	}
}
