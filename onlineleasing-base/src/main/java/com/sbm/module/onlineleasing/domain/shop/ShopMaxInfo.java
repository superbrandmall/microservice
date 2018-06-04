package com.sbm.module.onlineleasing.domain.shop;

import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
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

	public ShopMaxInfo(String code, Integer state, String unit, String mallCode, String mallName, String floorCode, String floorName, BigDecimal area, String modality, Date contractExpireDate, Integer shopState, String subType, String brandCode, String buildingCode, BigDecimal deadRent, BigDecimal floatingRentalRate, String shopName, String buildingName, String hdUuid, String hdCode, String hdState, String vr) {
		super(code, state, unit, mallCode, mallName, floorCode, floorName, area, modality, contractExpireDate, shopState, subType, brandCode, buildingCode, deadRent, floatingRentalRate, shopName, buildingName, hdUuid, hdCode, hdState, vr);
	}

	public static  ShopMaxInfo convert(TOLShop e) {
		return new ShopMaxInfo(e.getCode(), e.getState(), e.getUnit(), e.getMallCode(), e.getMallName(), e.getFloorCode(), e.getFloorName(), e.getArea(), e.getModality(), e.getContractExpireDate(), e.getShopState(), e.getSubType(),
				e.getBrandCode(), e.getBuildingCode(), e.getDeadRent(), e.getFloatingRentalRate(),
				e.getShopName(), e.getBuildingName(), e.getHdUuid(), e.getHdCode(), e.getHdState(), e.getVr());
	}

}
