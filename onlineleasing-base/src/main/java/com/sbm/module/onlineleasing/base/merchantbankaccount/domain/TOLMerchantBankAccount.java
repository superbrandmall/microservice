package com.sbm.module.onlineleasing.base.merchantbankaccount.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_ol_merchant_bank_account")
@Data
public class TOLMerchantBankAccount extends DataEntity {

	private String code;

	private String bankAccount;

	private String bankAccountDesc;

}
