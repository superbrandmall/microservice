package com.sbm.module.onlineleasing.customer.searchshop.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SearchShopResult {

	@ApiModelProperty(value = "查询结果")
	private List<ShopScore> result;

	@ApiModelProperty(value = "查询信息编号")
	private String searchShopDetailCode;

	public SearchShopResult(List<ShopScore> result, String searchShopDetailCode) {
		this.result = result;
		this.searchShopDetailCode = searchShopDetailCode;
	}

	public SearchShopResult() {

	}
}
