package com.sbm.module.onlineleasing.domain.brand;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Brand extends BrandMinInfo {

	@ApiModelProperty(value = "品牌城市")
	private String city;

	@ApiModelProperty(value = "属性")
	private Integer attribute;

	@ApiModelProperty(value = "类型")
	private Integer brandClass;

	@ApiModelProperty(value = "标准面积")
	private Integer standardArea;

	@ApiModelProperty(value = "目标群体")
	private Integer target;

	@ApiModelProperty(value = "客单价")
	private BigDecimal averageUnitPrice;

	@ApiModelProperty(value = "地区")
	private Integer location;

	@ApiModelProperty(value = "开店数量")
	private Integer shopAmount;

	@ApiModelProperty(value = "历史")
	private Integer history;

	@ApiModelProperty(value = "口碑")
	private Integer reputation;

	@ApiModelProperty(value = "市场份额")
	private String marketShare;

	@ApiModelProperty(value = "行业排名")
	private Integer rank;

	@ApiModelProperty(value = "坪效")
	private Integer compare;

	@ApiModelProperty(value = "是否加入正大")
	private Integer joined;

	@ApiModelProperty(value = "是否入住其他门店")
	private Integer joinOtherMall;

	@ApiModelProperty(value = "来源")
	private Integer source;

	@ApiModelProperty(value = "logo")
	private String logo;

	@ApiModelProperty(value = "品牌商铺样图")
	private List<String> brandShopSamples;

	private String hdUuid;

	private String hdCode;

	public Brand(String code, String name, String modality_1, String modality_2, String modality_3, String nameEng, Integer status, String hdState, String city, Integer attribute, Integer brandClass, Integer standardArea, Integer target, BigDecimal averageUnitPrice, Integer location, Integer shopAmount, Integer history, Integer reputation, String marketShare, Integer rank, Integer compare, Integer joined, Integer joinOtherMall, Integer source, String logo, List<String> brandShopSamples, String hdUuid, String hdCode) {
		super(code, name, modality_1, modality_2, modality_3, nameEng, status, hdState);
		this.city = city;
		this.attribute = attribute;
		this.brandClass = brandClass;
		this.standardArea = standardArea;
		this.target = target;
		this.averageUnitPrice = averageUnitPrice;
		this.location = location;
		this.shopAmount = shopAmount;
		this.history = history;
		this.reputation = reputation;
		this.marketShare = marketShare;
		this.rank = rank;
		this.compare = compare;
		this.joined = joined;
		this.joinOtherMall = joinOtherMall;
		this.source = source;
		this.logo = logo;
		this.brandShopSamples = brandShopSamples;
		this.hdUuid = hdUuid;
		this.hdCode = hdCode;
	}

	public Brand() {

	}
}
