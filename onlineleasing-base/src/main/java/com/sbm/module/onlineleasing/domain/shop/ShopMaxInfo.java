package com.sbm.module.onlineleasing.domain.shop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ShopMaxInfo extends Shop {

	@ApiModelProperty(value = "品牌名称")
	private String brandName;

	@ApiModelProperty(value = "铺位图片")
	private List<ShopImages> images;

	@ApiModelProperty(value = "工程图")
	private List<ShopEngineeringImages> engineeringImages;

	@ApiModelProperty(value = "工程条件")
	private List<ShopEngineeringSpecifications> engineeringSpecifications;

	@ApiModelProperty(value = "是否关注")
	private Boolean isMyFavourite = false;

	public ShopMaxInfo(String code, String unit, String mallCode, String mallName, String floorCode, String floorName, BigDecimal area, String modality, Date contractExpireDate, Integer shopState, String subType, String brandCode, String buildingCode, BigDecimal deadRent, BigDecimal floatingRentalRate, String shopName, String buildingName, String hdUuid, String hdCode, String hdState, String vr) {
		super(code, unit, mallCode, mallName, floorCode, floorName, area, modality, contractExpireDate, shopState, subType, brandCode, buildingCode, deadRent, floatingRentalRate, shopName, buildingName, hdUuid, hdCode, hdState, vr);
	}
}
