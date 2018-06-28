package com.sbm.module.onlineleasing.domain.base.info.mall;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MallTraffic {

	@ApiModelProperty(value = "类型")
	private String type;

	@ApiModelProperty(value = "类型（英文）")
	private String typeEng;

	@ApiModelProperty(value = "文本")
	private String text;

	@ApiModelProperty(value = "文本（英文）")
	private String textEng;

	@ApiModelProperty(value = "备注")
	private String remark;

	public MallTraffic(String type, String typeEng, String text, String textEng, String remark) {
		this.type = type;
		this.typeEng = typeEng;
		this.text = text;
		this.textEng = textEng;
		this.remark = remark;
	}

	public MallTraffic() {

	}
}
