package com.sbm.module.sync.bi.api.bi.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BiDetail {

	@ApiModelProperty(value = "日期")
	private Integer yyyymmdd;

	@ApiModelProperty(value = "项目代码")
	private String mallHdCode;

	@ApiModelProperty(value = "销售额")
	private BigDecimal salesTy;

	@ApiModelProperty(value = "客流")
	private BigDecimal upTy;

	@ApiModelProperty(value = "车流")
	private BigDecimal vehicleInTy;

	public BiDetail() {
	}

	public BiDetail(Integer yyyymmdd, String mallHdCode, BigDecimal upTy) {
		this.yyyymmdd = yyyymmdd;
		this.mallHdCode = mallHdCode;
		this.upTy = upTy;
	}

	public BiDetail(Integer yyyymmdd, String mallHdCode, BigDecimal salesTy, BigDecimal upTy, BigDecimal vehicleInTy) {
		this.yyyymmdd = yyyymmdd;
		this.mallHdCode = mallHdCode;
		this.salesTy = salesTy;
		this.upTy = upTy;
		this.vehicleInTy = vehicleInTy;
	}
}
