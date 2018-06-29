package com.sbm.module.onlineleasing.domain.searchshop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SearchShopMinInfo {

	@ApiModelProperty(value = "最小面积")
	private Integer minArea;

	@ApiModelProperty(value = "最大面积")
	private Integer maxArea;

	@ApiModelProperty(value = "项目编号列表")
	private List<String> mallCodes;

	public SearchShopMinInfo() {
	}

	public SearchShopMinInfo(Integer minArea, Integer maxArea, List<String> mallCodes) {
		this.minArea = minArea;
		this.maxArea = maxArea;
		this.mallCodes = mallCodes;
	}
}
