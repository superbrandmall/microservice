package com.sbm.module.onlineleasing.base.bidcontract.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_ol_bid_contract")
@Data
public class TOLBidContract extends DataEntity {

	private String code;

	@Column(columnDefinition = "text")
	private String pdfTemp;

	@Column(columnDefinition = "text")
	private String pdf;

	private String depositBillNumber;

	@Column(columnDefinition = "text")
	private String depositBillPdf;

}
