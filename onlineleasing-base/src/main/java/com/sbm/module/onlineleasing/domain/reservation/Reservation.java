package com.sbm.module.onlineleasing.domain.reservation;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Reservation<T> extends ReservationUserInfo {

	private Date reserveTime;

	private Integer rentalLength;

	private Date startDate;

	private Date endDate;

	private List<T> shops;

	private Date created;

	public Reservation(String userCode, String userName, String mobile, String email, String merchantCode, String merchantName, String brandCode, String brandName, String brandModality, Date reserveTime, Integer rentalLength, Date startDate, Date endDate) {
		super(userCode, userName, mobile, email, merchantCode, merchantName, brandCode, brandName, brandModality);
		this.reserveTime = reserveTime;
		this.rentalLength = rentalLength;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Reservation() {
	}
}
