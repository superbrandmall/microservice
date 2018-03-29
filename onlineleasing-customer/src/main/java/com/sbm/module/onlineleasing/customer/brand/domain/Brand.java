package com.sbm.module.onlineleasing.customer.brand.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Brand {

	@ApiModelProperty(value = "品牌编号")
	private String code;

	@ApiModelProperty(value = "品牌名称")
	@NotBlank
	private String name;

	@ApiModelProperty(value = "品牌城市")
	private String city;

	@ApiModelProperty(value = "属性")
	private Integer attribute;

	@ApiModelProperty(value = "类型")
	private Integer brandClass;

	@ApiModelProperty(value = "标准面积")
	private Integer standardArea;

	@ApiModelProperty(value = "二级业态")
	private String modality_1;

	@ApiModelProperty(value = "三级业态")
	private String modality_2;

	@ApiModelProperty(value = "四级业态")
	private String modality_3;

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

	@ApiModelProperty(value = "英文名称")
	private String nameEng;

	@ApiModelProperty(value = "logo")
	private String logo;

	@ApiModelProperty(value = "状态")
	private Integer status;

	@ApiModelProperty(value = "品牌商铺样图")
	private List<String> brandShopSamples;

	public Brand(String code, String name, String city, Integer attribute, Integer brandClass, Integer standardArea, String modality_1, String modality_2, String modality_3, Integer target, BigDecimal averageUnitPrice, Integer location, Integer shopAmount, Integer history, Integer reputation, String marketShare, Integer rank, Integer compare, Integer joined, Integer joinOtherMall, Integer source, String nameEng, String logo, Integer status, List<String> brandShopSamples) {
		this.code = code;
		this.name = name;
		this.city = city;
		this.attribute = attribute;
		this.brandClass = brandClass;
		this.standardArea = standardArea;
		this.modality_1 = modality_1;
		this.modality_2 = modality_2;
		this.modality_3 = modality_3;
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
		this.nameEng = nameEng;
		this.logo = logo;
		this.status = status;
		this.brandShopSamples = brandShopSamples;
	}

	public Brand() {

	}
}
