package com.sbm.module.onlineleasing.domain.shop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ShopCheck extends ShopMinInfo {

	@ApiModelProperty(value = "品牌编号")
	private String brandCode;

	@ApiModelProperty(value = "是否同步")
	private Integer isSync;

	@ApiModelProperty(value = "图片数量")
	private Integer imagesSize;

	@ApiModelProperty(value = "坐标")
	private String coords;

	@ApiModelProperty(value = "检查项")
	private List<String> checkItems = new ArrayList<>();

	public ShopCheck() {
	}

	public ShopCheck(String code, Integer state, String unit, String mallCode, String floorCode, BigDecimal area, String modality, Date contractExpireDate, Integer shopState, String subType, String brandCode, Integer isSync, Integer imagesSize, String coords) {
		super(code, state, unit, mallCode, floorCode, area, modality, contractExpireDate, shopState, subType);
		this.brandCode = brandCode;
		this.isSync = isSync;
		this.imagesSize = imagesSize;
		this.coords = coords;
	}
}
