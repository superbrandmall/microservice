package com.sbm.module.onlineleasing.base.merchantbusinesslicense.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_ol_merchant_business_license")
@Data
public class TOLMerchantBusinessLicense extends DataEntity {

	private String code;

	@Column(columnDefinition = "text")
	private String businessLicense;

}
