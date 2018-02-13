package com.sbm.module.onlineleasing.base.shopengineeringspecifications.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_ol_shop_engineering_specifications")
@Data
public class TOLShopEngineeringSpecifications extends DataEntity {

	private String code;

	private String keyword;

	private String name;

	private String title;

	private Integer number;

	private String spec;

}
