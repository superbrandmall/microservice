package com.sbm.module.onlineleasing.base.shopimages.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_ol_shop_images")
@Data
public class TOLShopImages extends DataEntity {

	private String code;

	@Column(columnDefinition = "text")
	private String image;

	private Integer position;

}
