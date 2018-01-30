package com.sbm.module.partner.hd.base.floor.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(schema = "hd40", name = "t_ol_floor")
@Data
public class Floor {

	/**
	 * UUID
	 */
	@Id
	private String hdUuid;

	/**
	 * 楼层代码
	 */
	private String hdCode;

	/**
	 * 楼层名称
	 */
	private String floorName;

	/**
	 * 所属建筑物
	 */
	private String buildingUuid;

	/**
	 * 所属项目
	 */
	private String mallUuid;

	/**
	 * 状态
	 */
	private String state;

	/**
	 * 建筑面积
	 */
	private BigDecimal grossFloorArea;

	/**
	 * 租赁面积
	 */
	private BigDecimal leasingArea;

	/**
	 * 备注
	 */
	private String description;

}
