package com.sbm.module.onlineleasing.base.brand.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "t_ol_brand")
@Data
public class TOLBrand extends DataEntity {

	private String code;

	private String name;

	private String city;

	private Integer attribute;

	@Column(name = "class")
	private Integer brandClass;

	private Integer standardArea;

	private String modality_1;

	private String modality_2;

	private String modality_3;

	private Integer target;

	private BigDecimal averageUnitPrice;

	private Integer location;

	private Integer shopAmount;

	private Integer history;

	private Integer reputation;

	private String marketShare;

	private Integer rank;

	private Integer compare;

	private Integer lawsuit;

	private Integer arrearsOfRent;

	private Integer taxEvasion;

	private Integer qualityProblem;

	private Integer joined;

	private Integer joinOtherMall;

	private Integer source;

	private String nameEng;

	@Column(columnDefinition = "text")
	private String logo;

	private Integer status;

	private String hdUuid;

	private String hdCode;

	private String hdState;

}
