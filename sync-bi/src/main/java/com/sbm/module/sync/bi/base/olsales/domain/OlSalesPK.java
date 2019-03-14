package com.sbm.module.sync.bi.base.olsales.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class OlSalesPK implements Serializable {

	private String contractNo;

	private String buildunit;

	private String yyyymmdd;

	private String brandName;

	public OlSalesPK() {
	}

	public OlSalesPK(String contractNo, String buildunit, String yyyymmdd, String brandName) {
		this.contractNo = contractNo;
		this.buildunit = buildunit;
		this.yyyymmdd = yyyymmdd;
		this.brandName = brandName;
	}
}
