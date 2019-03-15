package com.sbm.module.sync.bi.api.sales.domain;

import lombok.Data;

import java.util.List;

/**
 * @author bbjohn
 * @date 2019/3/14 16:24
 */
@Data
public class SalesResult {

	private List<Sales> sales;

	private String startdate;

	private String enddate;

	public SalesResult() {
	}

	public SalesResult(List<Sales> sales, String startdate, String enddate) {

		this.sales = sales;
		this.startdate = startdate;
		this.enddate = enddate;
	}
}
