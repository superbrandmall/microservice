package com.sbm.module.onlineleasing.domain.base.modality;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Modality {

	@ApiModelProperty(value = "编号")
	private String code;

	@ApiModelProperty(value = "名称")
	private String name;

	@ApiModelProperty(value = "级别")
	private String lv;

	@ApiModelProperty(value = "备注")
	private String remark;

	@ApiModelProperty(value = "子业态")
	private List<Modality> children = new ArrayList<>();

	public Modality(String code, String name, String lv, String remark) {
		this.code = code;
		this.name = name;
		this.lv = lv;
		this.remark = remark;
	}

	public Modality() {

	}
}
