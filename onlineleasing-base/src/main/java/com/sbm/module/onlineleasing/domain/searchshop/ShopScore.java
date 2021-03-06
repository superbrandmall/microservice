package com.sbm.module.onlineleasing.domain.searchshop;

import com.sbm.module.onlineleasing.domain.shop.ShopMinInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ShopScore extends ShopMinInfo {

	@ApiModelProperty(value = "得分")
	private BigDecimal score;

	public ShopScore(String code, Integer state, String unit, String mallCode, String floorCode, BigDecimal area, String modality, Date contractExpireDate, Integer shopState, String subType) {
		super(code, state, unit, mallCode, floorCode, area, modality, contractExpireDate, shopState, subType);
	}
}
