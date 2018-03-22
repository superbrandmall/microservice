package com.sbm.module.onlineleasing.domain.base.info.mall;

import com.sbm.module.onlineleasing.domain.base.info.ModalityProportion;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MallInfo {

	@ApiModelProperty(value = "商场编号")
	private String mallCode;

	@ApiModelProperty(value = "商场名称")
	private String mallName;

	@ApiModelProperty(value = "地址")
	private String location;

	@ApiModelProperty(value = "描述")
	private String description;

	@ApiModelProperty(value = "建筑面积")
	private BigDecimal grossFloorArea;

	@ApiModelProperty(value = "租赁面积")
	private BigDecimal leasingArea;

	@ApiModelProperty(value = "位置")
	private Integer position;

	@ApiModelProperty(value = "图片")
	private String img;

	@ApiModelProperty(value = "商场名称（英文）")
	private String mallNameEng;

	@ApiModelProperty(value = "地址（英文）")
	private String locationEng;

	@ApiModelProperty(value = "视频")
	private String video;

	@ApiModelProperty(value = "交通")
	List<MallTraffic> traffic;

	@ApiModelProperty(value = "业态占比")
	private ModalityProportion proportion;

}
