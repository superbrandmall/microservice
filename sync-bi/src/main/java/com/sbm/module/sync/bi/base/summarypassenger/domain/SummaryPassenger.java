package com.sbm.module.sync.bi.base.summarypassenger.domain;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(schema = "l_uedw", name = "edw_int_summary_passenger")
@Data
public class SummaryPassenger {

	@EmbeddedId
	private SummaryPassengerPK pk;

	private BigDecimal inSum;

}
