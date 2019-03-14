package com.sbm.module.sync.bi.api.sales.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Sales {

	@NotBlank
	private String contractNo;

	@NotBlank
	private String buildunit;

	@NotBlank
	private String yyyymmdd;

	@NotBlank
	private String brandName;

	private BigDecimal total;

	private Date begindate;

	private Date realenddate;

	public Sales() {
	}

	public Sales(String contractNo, String buildunit, String yyyymmdd, String brandName, BigDecimal total, Date begindate, Date realenddate) {
		this.contractNo = contractNo;
		this.buildunit = buildunit;
		this.yyyymmdd = yyyymmdd;
		this.brandName = brandName;
		this.total = total;
		this.begindate = begindate;
		this.realenddate = realenddate;
	}
}
