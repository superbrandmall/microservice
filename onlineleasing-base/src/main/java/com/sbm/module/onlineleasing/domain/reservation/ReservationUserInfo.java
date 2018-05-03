package com.sbm.module.onlineleasing.domain.reservation;

import lombok.Data;

@Data
public class ReservationUserInfo {

	private String userCode;

	private String userName;

	private String mobile;

	private String email;

	private String merchantCode;

	private String merchantName;

	private String brandCode;

	private String brandName;

	private String brandModality;


	public ReservationUserInfo() {
	}

	public ReservationUserInfo(String userCode, String userName, String mobile, String email, String merchantCode, String merchantName, String brandCode, String brandName, String brandModality) {

		this.userCode = userCode;
		this.userName = userName;
		this.mobile = mobile;
		this.email = email;
		this.merchantCode = merchantCode;
		this.merchantName = merchantName;
		this.brandCode = brandCode;
		this.brandName = brandName;
		this.brandModality = brandModality;
	}
}
