package com.sbm.module.sync.bi.base.olsales.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "v_ol_sales")
@Data
public class OlSales {

	@Id
	private OlSalesPK pk;

	private BigDecimal total;

	@Column(columnDefinition = "timestamp")
	private Date begindate;

	@Column(columnDefinition = "timestamp")
	private Date realenddate;
}
