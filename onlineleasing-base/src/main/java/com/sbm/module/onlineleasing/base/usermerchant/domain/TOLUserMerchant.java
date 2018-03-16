package com.sbm.module.onlineleasing.base.usermerchant.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_ol_user_merchant")
@Data
public class TOLUserMerchant extends DataEntity {

	private String userCode;

	private String merchantCode;

	public TOLUserMerchant(String userCode, String merchantCode) {
		this.userCode = userCode;
		this.merchantCode = merchantCode;
	}

	public TOLUserMerchant() {
	}
}
