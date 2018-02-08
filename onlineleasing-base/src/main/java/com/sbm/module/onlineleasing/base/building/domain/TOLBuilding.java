package com.sbm.module.onlineleasing.base.building.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "t_ol_building")
@Data
public class TOLBuilding extends DataEntity {

	private String code;

	private String mallCode;

	private String buildingName;

	private BigDecimal grossFloorArea;

	private BigDecimal leasingArea;

	private String description;

	private String hdUuid;

	private String hdCode;

	private String hdState;

}
