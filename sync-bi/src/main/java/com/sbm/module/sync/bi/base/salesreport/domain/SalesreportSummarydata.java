package com.sbm.module.sync.bi.base.salesreport.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(schema = "uedw", name = "edw_salesreport_summarydata_f")
@Data
public class SalesreportSummarydata {

	@Id
	@ApiModelProperty(value="日期")
	private Integer yyyymmdd;

	@ApiModelProperty(value="建筑物代码")
	private String buildingCode;

	@ApiModelProperty(value="销售额")
	private BigDecimal salesTy;

	@ApiModelProperty(value="客流")
	private BigDecimal upTy;

	@ApiModelProperty(value="车流")
	private BigDecimal vehicleInTy;

}
