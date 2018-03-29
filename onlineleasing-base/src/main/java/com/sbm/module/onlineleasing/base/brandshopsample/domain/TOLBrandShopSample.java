package com.sbm.module.onlineleasing.base.brandshopsample.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_ol_brand_shop_sample")
@Data
public class TOLBrandShopSample extends DataEntity {

	private String code;

	@Column(columnDefinition = "text")
	private String shopSample;

	public TOLBrandShopSample(String code, String shopSample) {
		this.code = code;
		this.shopSample = shopSample;
	}

	public TOLBrandShopSample() {

	}
}
