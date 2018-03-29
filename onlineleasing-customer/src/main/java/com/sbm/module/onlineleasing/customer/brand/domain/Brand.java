package com.sbm.module.onlineleasing.customer.brand.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Brand {

	@ApiModelProperty(value = "品牌编号")
	private String code;

	@ApiModelProperty(value = "品牌名称")
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

	private Integer target;

	private BigDecimal averageUnitPrice;

	private Integer location;

	private Integer shopAmount;

	private Integer history;

	private Integer reputation;

	private String marketShare;

	private Integer rank;

	private Integer compare;

	private Integer lawsuit;

	private Integer arrearsOfRent;

	private Integer taxEvasion;

	private Integer qualityProblem;

	private Integer joined;

	private Integer joinOtherMall;

	@ApiModelProperty(value = "来源")
	private Integer source;

	@ApiModelProperty(value = "英文名称")
	private String nameEng;

	@ApiModelProperty(value = "logo")
	private String logo;

	@ApiModelProperty(value = "品牌编号")
	private Integer status;

	@ApiModelProperty(value = "品牌编号")
	private String hdUuid;

	@ApiModelProperty(value = "品牌编号")
	private String hdCode;

	@ApiModelProperty(value = "品牌编号")
	private String hdState;

	@ApiModelProperty(value = "品牌商铺样图")
	private List<String> brandShopSamples;

	public Brand(String code, String name, String city, Integer attribute, Integer brandClass, Integer standardArea, String modality_1, String modality_2, String modality_3, Integer target, BigDecimal averageUnitPrice, Integer location, Integer shopAmount, Integer history, Integer reputation, String marketShare, Integer rank, Integer compare, Integer lawsuit, Integer arrearsOfRent, Integer taxEvasion, Integer qualityProblem, Integer joined, Integer joinOtherMall, Integer source, String nameEng, String logo, Integer status, String hdUuid, String hdCode, String hdState, List<String> brandShopSamples) {
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
		this.lawsuit = lawsuit;
		this.arrearsOfRent = arrearsOfRent;
		this.taxEvasion = taxEvasion;
		this.qualityProblem = qualityProblem;
		this.joined = joined;
		this.joinOtherMall = joinOtherMall;
		this.source = source;
		this.nameEng = nameEng;
		this.logo = logo;
		this.status = status;
		this.hdUuid = hdUuid;
		this.hdCode = hdCode;
		this.hdState = hdState;
		this.brandShopSamples = brandShopSamples;
	}

	public Brand() {

	}
}
