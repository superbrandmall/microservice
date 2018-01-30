package com.sbm.module.partner.hd.api.mall.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class HdMall {

	@ApiModelProperty(value="UUID")
	private String hdUuid;

	@ApiModelProperty(value="项目代码")
	private String hdCode;

	@ApiModelProperty(value="项目名称")
	private String hdName;

	@ApiModelProperty(value="地址")
	private String location;

	@ApiModelProperty(value="建筑面积")
	private BigDecimal grossFloorArea;

	@ApiModelProperty(value="租赁面积")
	private BigDecimal leasingArea;

	@ApiModelProperty(value="备注")
	private String description;

	@ApiModelProperty(value="状态")
	private String state;

	public HdMall() {
	}

	public HdMall(String hdUuid, String hdCode, String hdName, String location, BigDecimal grossFloorArea, BigDecimal leasingArea, String description, String state) {
		this.hdUuid = hdUuid;
		this.hdCode = hdCode;
		this.hdName = hdName;
		this.location = location;
		this.grossFloorArea = grossFloorArea;
		this.leasingArea = leasingArea;
		this.description = description;
		this.state = state;
	}
}
