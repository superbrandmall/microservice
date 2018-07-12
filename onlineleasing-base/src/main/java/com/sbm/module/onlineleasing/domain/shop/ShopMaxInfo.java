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

	@ApiModelProperty(value = "品牌名称（英文）")
	private String brandNameEng;

	@ApiModelProperty(value = "铺位图片")
	private List<ShopImages> images;

	@ApiModelProperty(value = "工程图")
	private List<ShopEngineeringImages> engineeringImages;

	@ApiModelProperty(value = "工程条件")
	private List<ShopEngineeringSpecifications> engineeringSpecifications;

	@ApiModelProperty(value = "是否关注")
	private Boolean isMyFavourite = false;

	@ApiModelProperty(value = "坐标")
	private String coords;

	public ShopMaxInfo() {
	}

	public ShopMaxInfo(String code, Integer state, String unit, String mallCode, String floorCode, BigDecimal area, String modality, Date contractExpireDate, Integer shopState, String subType, String brandCode, String buildingCode, BigDecimal deadRent, BigDecimal floatingRentalRate, String shopName, String hdUuid, String hdCode, String hdState, String vr) {
		super(code, state, unit, mallCode, floorCode, area, modality, contractExpireDate, shopState, subType, brandCode, buildingCode, deadRent, floatingRentalRate, shopName, hdUuid, hdCode, hdState, vr);
	}

	public static ShopMaxInfo convert(TOLShop e) {
		return new ShopMaxInfo(e.getCode(), e.getState(), e.getUnit(), e.getMallCode(), e.getFloorCode(), e.getArea(), e.getModality(), e.getContractExpireDate(), e.getShopState(), e.getSubType(),
				e.getBrandCode(), e.getBuildingCode(), e.getDeadRent(), e.getFloatingRentalRate(),
				e.getShopName(), e.getHdUuid(), e.getHdCode(), e.getHdState(), e.getVr());
	}

	public static ShopMaxInfo convertAll(TOLShop e) {
		ShopMaxInfo vo = convert(e);
		vo.setIsSync(e.getIsSync());
		vo.setVrValidated(e.getVrValidated());
		return vo;
	}

}
