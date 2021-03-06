package com.sbm.module.onlineleasing.domain.base.info.floor;

import com.sbm.module.onlineleasing.domain.base.info.ModalityProportion;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FloorInfo extends FloorMinInfo {

	@ApiModelProperty(value = "项目编号")
	private String mallCode;

	@ApiModelProperty(value = "建筑面积")
	private BigDecimal grossFloorArea;

	@ApiModelProperty(value = "租赁面积")
	private BigDecimal leasingArea;

	@ApiModelProperty(value = "业态占比")
	private ModalityProportion proportion;

}
