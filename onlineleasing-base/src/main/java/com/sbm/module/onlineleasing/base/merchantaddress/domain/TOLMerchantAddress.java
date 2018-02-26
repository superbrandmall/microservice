package com.sbm.module.onlineleasing.base.merchantaddress.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_ol_merchant_address")
@Data
public class TOLMerchantAddress extends DataEntity {

	private String code;

	private String province;

	private String city;

	private String postalCode;

	/**
	 * 注册地址
	 */
	private String streetAddress;

	/**
	 * 寄件地址
	 */
	private String mailingAddress;

	private String fax;

}
