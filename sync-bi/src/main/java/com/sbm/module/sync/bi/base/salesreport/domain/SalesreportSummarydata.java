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
	private SalesreportSummarydataPK pk;

	private BigDecimal salesTy;

	private BigDecimal upTy;

	private BigDecimal vehicleInTy;

}
