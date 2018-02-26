package com.sbm.module.partner.hd.rest.merchant.domain;

import lombok.Data;

@Data
public class HdMerchantProperties {

	/**
	 * 注册地址
	 */
	private String registerAddress;

	/**
	 * 注册资金
	 */
	private String regCapital;

	/**
	 * 股东结构
	 */
	private String shareholder;

	/**
	 * 统一社会信用代码
	 */
	private String uscc;

	/**
	 * 经营范围
	 */
	private String business_scope;

	/**
	 * 天眼查ID
	 */
	private String tianyancha_id;

	/**
	 * 邮寄地址
	 */
	private String postAddress;

	/**
	 * 营业执照
	 */
	private String business_licence;

	/**
	 * OL商户访问URL
	 */
	private String ol_url;

}
