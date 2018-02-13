package com.sbm.module.onlineleasing.base.shopengineeringimages.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_ol_shop_engineering_images")
@Data
public class TOLShopEngineeringImages extends DataEntity {

	private String code;

	private String attachmentType;

	@Column(columnDefinition = "text")
	private String image;

}
