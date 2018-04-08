package com.sbm.module.onlineleasing.base.shopcoords.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_ol_shop_coords")
@Data
public class TOLShopCoords extends DataEntity {

	private String code;

	private String buildingCode;

	@Column(columnDefinition = "text")
	private String coords;

	private String propertyNumber;

	private String unit;

	private String shopName;

}
