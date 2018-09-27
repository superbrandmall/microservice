package com.sbm.module.onlineleasing.domain.searchshop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SearchShop extends SearchShopMinInfo {

	@ApiModelProperty(value = "用户编号")
	private String userCode;

	@ApiModelProperty(value = "品牌编号")
	private String brandCode;

	@ApiModelProperty(value = "品牌名称")
	private String brandName;

	@ApiModelProperty(value = "品牌业态")
	private String brandModality;

	@ApiModelProperty(value = "租赁年限")
	private Integer rentalLength;

	@ApiModelProperty(value = "开始日期")
	private Date startDate;

	@ApiModelProperty(value = "结束日期")
	private Date endDate;

	@ApiModelProperty(value = "创建时间")
	private Date created;

	@ApiModelProperty(value = "返回结果数量，默认24")
	private Integer max = 24;

	@ApiModelProperty(value = "子类型")
	private String subType;

	public SearchShop() {
	}

	public SearchShop(Integer minArea, Integer maxArea, List<String> mallCodes, String userCode, String brandCode, String brandName, String brandModality, Integer rentalLength, Date startDate, Date endDate, Date created, String subType) {
		super(minArea, maxArea, mallCodes);
		this.userCode = userCode;
		this.brandCode = brandCode;
		this.brandName = brandName;
		this.brandModality = brandModality;
		this.rentalLength = rentalLength;
		this.startDate = startDate;
		this.endDate = endDate;
		this.created = created;
		this.subType = subType;
	}
}
