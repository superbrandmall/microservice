package com.sbm.module.onlineleasing.base.myfavourite.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_ol_my_favourite")
@Data
public class TOLMyFavourite extends DataEntity {

	private String userCode;

	private String shopCode;

	public TOLMyFavourite() {
	}

	public TOLMyFavourite(String userCode, String shopCode) {

		this.userCode = userCode;
		this.shopCode = shopCode;
	}
}
