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

	@ApiModelProperty(value = "品牌名称")
	private String brandName;

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

	@ApiModelProperty(value = "创建时间")
	private Date created;

	@ApiModelProperty(value = "返回结果数量，默认24")
	private Integer max = 24;

	public SearchShop() {
	}

	public SearchShop(String userCode, String brandCode, String brandName, Integer minArea, Integer maxArea, Date start, Date end, List<String> mallCodes, Date created) {
		this.userCode = userCode;
		this.brandCode = brandCode;
		this.brandName = brandName;
		this.minArea = minArea;
		this.maxArea = maxArea;
		this.start = start;
		this.end = end;
		this.mallCodes = mallCodes;
		this.created = created;
	}
}
