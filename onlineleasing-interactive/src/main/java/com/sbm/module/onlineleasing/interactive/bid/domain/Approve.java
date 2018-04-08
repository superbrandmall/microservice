package com.sbm.module.onlineleasing.interactive.bid.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class Approve {

	@ApiModelProperty(value = "意向合同编号")
	@NotNull
	private String billNumber;

	@ApiModelProperty(value = "审批结果:1-审批通过,2-审批不通过,其他返回异常")
	@NotNull
	private Integer isApprove;

	@ApiModelProperty(value = "过期日期,审批通过则该项为必填")
	private Date expireDate;

}
