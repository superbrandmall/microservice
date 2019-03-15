package com.sbm.module.sync.bi.api.sales.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author bbjohn
 * @date 2019/3/14 16:25
 */
@Data
public class SalesSum {

	private Integer weeks;

	private BigDecimal total;

	private String startdate;

	private String enddate;

	public SalesSum() {
	}

	public SalesSum(Integer weeks, BigDecimal total, String startdate, String enddate) {
		this.weeks = weeks;
		this.total = total;
		this.startdate = startdate;
		this.enddate = enddate;
	}
}
