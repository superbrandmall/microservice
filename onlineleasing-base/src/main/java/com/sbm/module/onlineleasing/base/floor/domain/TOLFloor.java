package com.sbm.module.onlineleasing.base.floor.domain;

import com.sbm.module.common.data.domain.DataEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "t_ol_floor")
@Data
public class TOLFloor extends DataEntity {

	@ApiModelProperty(value = "楼层编号")
	private String code;

	@ApiModelProperty(value = "建筑物编号")
	private String buildingCode;

	@ApiModelProperty(value = "楼层名称")
	private String floorName;

	@ApiModelProperty(value = "建筑面积")
	private BigDecimal grossFloorArea;

	@ApiModelProperty(value = "租赁面积")
	private BigDecimal leasingArea;

	@ApiModelProperty(value = "描述")
	private String description;

	@ApiModelProperty(value = "海鼎uuid")
	private String hdUuid;

	@ApiModelProperty(value = "海鼎编码")
	private String hdCode;

	@ApiModelProperty(value = "海鼎海鼎状态")
	private String hdState;

}
