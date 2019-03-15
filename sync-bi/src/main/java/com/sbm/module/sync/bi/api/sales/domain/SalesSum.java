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

	public SalesSum() {
	}

	public SalesSum(Integer weeks, BigDecimal total) {

		this.weeks = weeks;
		this.total = total;
	}
}
