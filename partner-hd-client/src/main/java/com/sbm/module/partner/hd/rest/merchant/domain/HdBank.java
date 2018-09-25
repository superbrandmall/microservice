package com.sbm.module.partner.hd.rest.merchant.domain;

import lombok.Data;

@Data
public class HdBank {

	/**
	 * 银行名称
	 */
	private String name;

	/**
	 * 银行账号
	 */
	private String account;

	/**
	 * 银行账号用途
	 */
	private String bankAccountUse;

	public HdBank() {
	}

	public HdBank(String name, String account, String bankAccountUse) {

		this.name = name;
		this.account = account;
		this.bankAccountUse = bankAccountUse;
	}
}
