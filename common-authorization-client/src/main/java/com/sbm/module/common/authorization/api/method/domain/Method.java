package com.sbm.module.common.authorization.api.method.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Method {

	@ApiModelProperty(value = "明细")
	private List<MethodDetail> details = new ArrayList<>();

}
