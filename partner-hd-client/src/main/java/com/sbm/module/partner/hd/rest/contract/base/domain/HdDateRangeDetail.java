package com.sbm.module.partner.hd.rest.contract.base.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class HdDateRangeDetail {

	@ApiModelProperty(value = "开始日期")
	private Date beginDate;

	@ApiModelProperty(value = "结束日期")
	private Date endDate;

	@ApiModelProperty(value = "金额或比例值")
	private BigDecimal value;

}
