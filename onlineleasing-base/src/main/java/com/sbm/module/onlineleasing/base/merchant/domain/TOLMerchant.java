package com.sbm.module.onlineleasing.base.merchant.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_ol_merchant")
@Data
public class TOLMerchant extends DataEntity {

	private String code;

	private String name;

	private Integer type;

	/**
	 * 注册资金
	 */
	private String capital;

	private String shareholder;

	private String uscc;

	private Integer authState;

	private String businessScope;

	private Long tianyanchaId;

	private String hdUuid;

	private String hdCode;

	private String hdState;

}
