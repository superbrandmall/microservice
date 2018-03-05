package com.sbm.module.onlineleasing.domain.info;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ModalityProportion {

	@ApiModelProperty(value = "占比明细")
	private List<ModalityProportionDetail> details = new ArrayList<>();

}
