package com.sbm.module.partner.hd.rest.contract.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class HdEnteryTerm {

	@ApiModelProperty(value = "条款名称")
	private String caption;

	@ApiModelProperty(value = "说明")
	private String remark;

	@ApiModelProperty(value = "交付日期")
	private Date deliveryDate;

	@ApiModelProperty(value = "进场日期")
	private Date entryDate;

	@ApiModelProperty(value = "开业日期")
	private Date openDate;

	@ApiModelProperty(value = "装修开始日期")
	private Date fitmentBeginDate;

	@ApiModelProperty(value = "装修结束日期")
	private Date fitmentEndDate;

	@ApiModelProperty(value = "装修天数")
	private Integer fitmentDays;

	@ApiModelProperty(value = "计租日期")
	private Date rentDate;

	@ApiModelProperty(value = "经营免租期天数")
	private Integer freeDays;

	@ApiModelProperty(value = "经营免租期月")
	private Integer freeMonths;

}
