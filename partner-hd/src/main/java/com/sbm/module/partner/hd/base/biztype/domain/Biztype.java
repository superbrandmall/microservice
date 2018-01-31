package com.sbm.module.partner.hd.base.biztype.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(schema = "hd40", name = "t_ol_biztype")
@Data
public class Biztype {

	/**
	 * UUID
	 */
	@Id
	private String hdUuid;

	/**
	 * 当前id
	 */
	private String levelid;

	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 上级id
	 */
	private String upperid;

	/**
	 * 备注（目前存放英文名称）
	 */
	private String remark;

}
