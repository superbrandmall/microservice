package com.sbm.module.onlineleasing.base.floor.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "t_ol_floor")
@Data
public class TOLFloor extends DataEntity {

	private String code;

	private String buildingCode;

	private String floorName;

	private BigDecimal grossFloorArea;

	private BigDecimal leasingArea;

	private String description;

	private String hdUuid;

	private String hdCode;

	private String hdState;

}
