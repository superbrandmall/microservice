package com.sbm.module.sync.bi.base.summarypassenger.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Embeddable
public class SummaryPassengerPK implements Serializable{

	private Integer yyyymmdd;

	private String siteKey;

	private String mallCode;

}
