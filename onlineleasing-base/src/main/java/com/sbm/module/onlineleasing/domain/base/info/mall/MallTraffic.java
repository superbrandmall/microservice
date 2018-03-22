package com.sbm.module.onlineleasing.domain.base.info.mall;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MallTraffic {

	@ApiModelProperty(value = "类型")
	private String type;

	@ApiModelProperty(value = "文本")
	private String text;

	@ApiModelProperty(value = "备注")
	private String remark;

	public MallTraffic(String type, String text, String remark) {
		this.type = type;
		this.text = text;
		this.remark = remark;
	}

	public MallTraffic() {

	}
}
