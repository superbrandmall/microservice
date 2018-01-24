package com.sbm.module.sync.bi.base.salesreport.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class SalesreportSummarydataPK implements Serializable{

	private Integer yyyymmdd;

	private String buildingCode;

}
