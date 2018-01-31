package com.sbm.module.partner.hd.base.building.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(schema = "hd40", name = "t_ol_building")
@Data
public class Building {

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
	 * 建筑物名称
	 */
	private String buildingName;

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
