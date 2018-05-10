package com.sbm.module.onlineleasing.domain.shop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ShopMinInfo {

	@ApiModelProperty(value = "铺位编号")
	private String code;

	@ApiModelProperty(value = "状态")
	private Integer state;

	@ApiModelProperty(value = "单元")
	private String unit;

	@ApiModelProperty(value = "项目编号")
	private String mallCode;

	@ApiModelProperty(value = "项目名称")
	private String mallName;

	@ApiModelProperty(value = "楼层编号")
	private String floorCode;

	@ApiModelProperty(value = "楼层名称")
	private String floorName;

	@ApiModelProperty(value = "面积")
	private BigDecimal area;

	@ApiModelProperty(value = "业态")
	private String modality;

	@ApiModelProperty(value = "最早可入住时间")
	private Date contractExpireDate;

	@ApiModelProperty(value = "铺位状态")
	private Integer shopState;

	@ApiModelProperty(value = "铺位子类型")
	private String subType;

	@ApiModelProperty(value = "图片")
	private String firstImage;

	public ShopMinInfo(String code, Integer state, String unit, String mallCode, String mallName, String floorCode, String floorName, BigDecimal area, String modality, Date contractExpireDate, Integer shopState, String subType) {
		this.code = code;
		this.state = state;
		this.unit = unit;
		this.mallCode = mallCode;
		this.mallName = mallName;
		this.floorCode = floorCode;
		this.floorName = floorName;
		this.area = area;
		this.modality = modality;
		this.contractExpireDate = contractExpireDate;
		this.shopState = shopState;
		this.subType = subType;
	}

	public ShopMinInfo() {
	}
}
