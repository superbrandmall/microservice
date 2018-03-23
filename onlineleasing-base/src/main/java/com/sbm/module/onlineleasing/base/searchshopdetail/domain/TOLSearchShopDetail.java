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

	private Integer minArea;

	private Integer maxArea;

	@Column(columnDefinition = "timestamp")
	private Date start;

	@Column(columnDefinition = "timestamp")
	private Date end;

	@Column(columnDefinition = "text")
	private String mallCodes;

	public TOLSearchShopDetail(String code, String userCode, String brandCode, Integer minArea, Integer maxArea, Date start, Date end, String mallCodes) {
		this.code = code;
		this.userCode = userCode;
		this.brandCode = brandCode;
		this.minArea = minArea;
		this.maxArea = maxArea;
		this.start = start;
		this.end = end;
		this.mallCodes = mallCodes;
	}

	public TOLSearchShopDetail() {

	}
}
