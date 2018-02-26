package com.sbm.module.partner.hd.rest.merchant.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HdMerchant {

	private String uuid;

	private String code;

	private String name;

	private String state;

	/**
	 * 类型
	 */
	private String type;

	private HdMerchantProperties properties = new HdMerchantProperties();

	private List<HdBank> banks = new ArrayList<>();

}
