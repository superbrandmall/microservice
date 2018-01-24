package com.sbm.module.sync.bi.api.bi.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class Bi {

	@ApiModelProperty(value="项目代码")
	private String mallCode;

	@ApiModelProperty(value="明细")
	private List<BiDetail> details;

}
