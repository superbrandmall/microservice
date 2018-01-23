package com.sbm.module.sync.bi.api.bi.domain;

import com.sbm.module.sync.bi.base.salesreport.domain.SalesreportSummarydata;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class Bi {

	@ApiModelProperty(value="建筑物代码")
	private String buildingCode;

	@ApiModelProperty(value="明细")
	private List<SalesreportSummarydata> details;

}
