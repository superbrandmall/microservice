package com.sbm.module.onlineleasing.domain.base.info.mall;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MallMinInfo extends MallBaseInfo {

	@ApiModelProperty(value = "地址")
	private String location;

	@ApiModelProperty(value = "地址（英文）")
	private String locationEng;

	@ApiModelProperty(value = "位置")
	private Integer position;

	@ApiModelProperty(value = "图片")
	private String img;

	@ApiModelProperty(value = "电话")
	private String phone;

	public MallMinInfo() {
	}

	public MallMinInfo(String mallCode, String mallName, String mallNameEng, String location, String locationEng, Integer position, String img, String phone) {
		super(mallCode, mallName, mallNameEng);
		this.location = location;
		this.locationEng = locationEng;
		this.position = position;
		this.img = img;
		this.phone = phone;
	}
}
