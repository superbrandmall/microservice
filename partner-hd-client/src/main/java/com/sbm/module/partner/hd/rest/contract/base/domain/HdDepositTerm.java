package com.sbm.module.partner.hd.rest.contract.base.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HdDepositTerm {

	@ApiModelProperty(value = "条款名称")
	private String caption;

	@ApiModelProperty(value = "说明")
	private String remark;

	@ApiModelProperty(value = "明细")
	private List<HdDepositTermDetail> details = new ArrayList<>();

	public HdDepositTerm(String caption, String remark) {
		this.caption = caption;
		this.remark = remark;
	}

	public HdDepositTerm() {

	}
}
