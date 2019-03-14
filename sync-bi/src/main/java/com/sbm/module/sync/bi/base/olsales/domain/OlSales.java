package com.sbm.module.sync.bi.base.olsales.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "v_ol_sales")
@Data
public class OlSales {

	private String contractNo;

	@EmbeddedId
	private OlSalesPK pk;

	private BigDecimal total;

	@Column(columnDefinition = "timestamp")
	private Date begindate;

	@Column(columnDefinition = "timestamp")
	private Date realenddate;

	public OlSales(String contractNo, OlSalesPK pk, BigDecimal total, Date begindate, Date realenddate) {
		this.contractNo = contractNo;
		this.pk = pk;
		this.total = total;
		this.begindate = begindate;
		this.realenddate = realenddate;
	}

	public OlSales() {
	}
}
