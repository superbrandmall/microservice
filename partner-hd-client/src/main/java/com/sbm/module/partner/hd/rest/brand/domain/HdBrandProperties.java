package com.sbm.module.partner.hd.rest.brand.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class HdBrandProperties {

	@ApiModelProperty(value = "品牌属性")
	private String introductions;

	@ApiModelProperty(value = "品牌价位")
	private String brandGrade;

	@ApiModelProperty(value = "标准店面积")
	private String areaLow;

	@ApiModelProperty(value = "主要客户群")
	private String target;

	@ApiModelProperty(value = "开店区域")
	private String location;

	@ApiModelProperty(value = "当前已开店数")
	private String shopCount;

	@ApiModelProperty(value = "品牌发展历史")
	private String history;

	@ApiModelProperty(value = "口碑")
	private String reputation;

	@ApiModelProperty(value = "是否有旗下品牌已入驻")
	private String joined;

	@ApiModelProperty(value = "是否有意进驻正大其它门店")
	private String join_other_mall;

	@ApiModelProperty(value = "月均销售额坪效")
	private String compare;

	@ApiModelProperty(value = "品牌信息来源")
	private String source;

	@ApiModelProperty(value = "平均客单价")
	private BigDecimal priceLow;

}
