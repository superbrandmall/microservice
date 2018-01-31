package com.sbm.module.partner.hd.api.biztype.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class HdBiztype {

	@ApiModelProperty(value = "UUID")
	private String hdUuid;

	@ApiModelProperty(value = "当前id")
	private String levelid;

	@ApiModelProperty(value = "代码")
	private String code;

	@ApiModelProperty(value = "名称")
	private String name;

	@ApiModelProperty(value = "上级id")
	private String upperid;

	@ApiModelProperty(value = "备注（目前存放英文名称）")
	private String remark;

	public HdBiztype() {
	}

	public HdBiztype(String hdUuid, String levelid, String code, String name, String upperid, String remark) {
		this.hdUuid = hdUuid;
		this.levelid = levelid;
		this.code = code;
		this.name = name;
		this.upperid = upperid;
		this.remark = remark;
	}
}
