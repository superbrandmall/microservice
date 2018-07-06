package com.sbm.module.onlineleasing.domain.shop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Shop extends ShopMinInfo {

	@ApiModelProperty(value = "品牌编号")
	private String brandCode;

	@ApiModelProperty(value = "建筑物编号")
	private String buildingCode;

	@ApiModelProperty(value = "固定租金")
	private BigDecimal deadRent;

	@ApiModelProperty(value = "浮动扣率")
	private BigDecimal floatingRentalRate;

	@ApiModelProperty(value = "门牌号")
	private String shopName;

	@ApiModelProperty(value = "海鼎uuid")
	private String hdUuid;

	@ApiModelProperty(value = "海鼎编码")
	private String hdCode;

	@ApiModelProperty(value = "海鼎状态")
	private String hdState;

	@ApiModelProperty(value = "vr地址")
	private String vr;

	@ApiModelProperty(value = "是否同步")
	private Integer isSync;

	public Shop() {
	}

	public Shop(String code, Integer state, String unit, String mallCode, String floorCode, BigDecimal area, String modality, Date contractExpireDate, Integer shopState, String subType, String brandCode, String buildingCode, BigDecimal deadRent, BigDecimal floatingRentalRate, String shopName, String hdUuid, String hdCode, String hdState, String vr) {
		super(code, state, unit, mallCode, floorCode, area, modality, contractExpireDate, shopState, subType);
		this.brandCode = brandCode;
		this.buildingCode = buildingCode;
		this.deadRent = deadRent;
		this.floatingRentalRate = floatingRentalRate;
		this.shopName = shopName;
		this.hdUuid = hdUuid;
		this.hdCode = hdCode;
		this.hdState = hdState;
		this.vr = vr;
	}
}
