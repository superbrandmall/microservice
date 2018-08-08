package com.sbm.module.onlineleasing.interactive.website.modality.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Modality {

	@ApiModelProperty(value = "编号")
	private String code;

	@ApiModelProperty(value = "Mall名称")
	private String name;

	@ApiModelProperty(value = "Mall名称（英文）")
	private String nameEng;

	@ApiModelProperty(value = "地址")
	private String lv;

	public Modality(String code, String name, String nameEng, String lv) {
		this.code = code;
		this.name = name;
		this.nameEng = nameEng;
		this.lv = lv;
	}

	public Modality() {

	}
}
