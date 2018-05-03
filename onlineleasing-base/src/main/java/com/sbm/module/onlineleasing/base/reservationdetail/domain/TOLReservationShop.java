package com.sbm.module.onlineleasing.base.reservationdetail.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_ol_reservation_shop")
@Data
public class TOLReservationShop extends DataEntity {

	private String reservationCode;

	private String shopCode;

	public TOLReservationShop(String reservationCode, String shopCode) {
		this.reservationCode = reservationCode;
		this.shopCode = shopCode;
	}

	public TOLReservationShop() {

	}
}
