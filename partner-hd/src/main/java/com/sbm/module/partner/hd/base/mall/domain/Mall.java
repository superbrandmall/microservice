package com.sbm.module.partner.hd.base.mall.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(schema = "hd40", name = "t_ol_mall")
@Data
public class Mall {

	/**
	 * UUID
	 */
	@Id
	private String hdUuid;

	/**
	 * 项目代码
	 */
	private String hdCode;

	/**
	 * 项目名称
	 */
	private String hdName;

	/**
	 * 地址
	 */
	private String location;

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

	/**
	 * 状态
	 */
	private String state;

}
