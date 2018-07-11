package com.sbm.module.onlineleasing.domain.base.info.mall;

import com.sbm.module.onlineleasing.domain.base.info.ModalityProportion;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MallInfo extends MallMinInfo {

	@ApiModelProperty(value = "描述")
	private String description;

	@ApiModelProperty(value = "描述（英文）")
	private String descriptionEng;

	@ApiModelProperty(value = "建筑面积")
	private BigDecimal grossFloorArea;

	@ApiModelProperty(value = "租赁面积")
	private BigDecimal leasingArea;

	@ApiModelProperty(value = "视频")
	private String video;

	@ApiModelProperty(value = "交通")
	private List<MallTraffic> traffic;

	@ApiModelProperty(value = "业态占比")
	private ModalityProportion proportion;

}
