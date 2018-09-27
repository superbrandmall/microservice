package com.sbm.module.onlineleasing.base.searchshopdetail.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "t_ol_search_shop_detail")
@Data
public class TOLSearchShopDetail extends DataEntity {

	private String code;

	private String userCode;

	private String brandCode;

	private String brandName;

	private String brandModality;

	private Integer minArea;

	private Integer maxArea;

	private Integer rentalLength;

	@Column(columnDefinition = "timestamp")
	private Date startDate;

	@Column(columnDefinition = "timestamp")
	private Date endDate;

	@Column(columnDefinition = "text")
	private String mallCodes;

	private String subType;

	public TOLSearchShopDetail() {

	}
}
