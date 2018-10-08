package com.sbm.module.onlineleasing.init.merchant.domain;

import lombok.Data;

@Data
public class MerchantCheck {

	private String uscc;

	private String dbName;

	private String tianyanchaName;

	private String reason;

	public MerchantCheck() {
	}

	public MerchantCheck(String uscc, String dbName) {
		this.uscc = uscc;
		this.dbName = dbName;
	}
}
