package com.sbm.module.onlineleasing.domain.merchant;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MerchantBankAccount {

	@ApiModelProperty(value = "银行账号")
	private String bankAccount;

	@ApiModelProperty(value = "银行账号描述")
	private String bankAccountDesc;

	public MerchantBankAccount() {
	}

	public MerchantBankAccount(String bankAccount, String bankAccountDesc) {

		this.bankAccount = bankAccount;
		this.bankAccountDesc = bankAccountDesc;
	}
}
