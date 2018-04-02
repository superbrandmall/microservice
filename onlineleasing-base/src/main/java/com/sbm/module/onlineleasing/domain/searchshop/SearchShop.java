package com.sbm.module.onlineleasing.domain.searchshop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SearchShop {

	@ApiModelProperty(value = "用户编号")
	private String userCode;

	@ApiModelProperty(value = "品牌编号")
	private String brandCode;

	@ApiModelProperty(value = "最小面积")
	private Integer minArea;

	@ApiModelProperty(value = "最大面积")
	private Integer maxArea;

	@ApiModelProperty(value = "开始日期")
	private Date start;

	@ApiModelProperty(value = "结束日期")
	private Date end;

	@ApiModelProperty(value = "项目编号列表")
	private List<String> mallCodes;

	@ApiModelProperty(value = "返回结果数量，默认24")
	private Integer max = 24;

}
