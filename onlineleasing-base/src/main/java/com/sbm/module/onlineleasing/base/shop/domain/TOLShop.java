package com.sbm.module.onlineleasing.base.shop.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "t_ol_shop")
@Data
public class TOLShop extends DataEntity {

	private String code;

	private String brandCode;

	private String mallCode;

	private String buildingCode;

	private String floorCode;

	private String unit;

	private BigDecimal area;

	private String modality;

	private Integer shopState;

	@Column(columnDefinition = "timestamp")
	private Date contractExpireDate;

	private BigDecimal deadRent;

	private BigDecimal floatingRentalRate;

	private String shopName;

	private String mallName;

	private String buildingName;

	private String floorName;

	private String hdUuid;

	private String hdCode;

	private String hdState;

	@Column(columnDefinition = "text")
	private String vr;

	private String subType;

	private Integer isSync;

}
