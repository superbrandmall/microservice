package com.sbm.module.onlineleasing.base.merchantbrand.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_ol_merchant_brand")
@Data
public class TOLMerchantBrand extends DataEntity {

	private String brandCode;

	private String merchantCode;

	private Integer creator;

	@Column(columnDefinition = "text")
	private String brandAuthor;
}
