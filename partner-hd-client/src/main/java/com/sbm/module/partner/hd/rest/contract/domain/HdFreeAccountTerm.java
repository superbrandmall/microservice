package com.sbm.module.partner.hd.rest.contract.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HdFreeAccountTerm {

	@ApiModelProperty(value = "条款名称")
	private String caption;

	@ApiModelProperty(value = "说明")
	private String remark;

	@ApiModelProperty(value = "明细")
	private List<HdDateRangeDetail> details = new ArrayList<>();

}
