package com.sbm.module.onlineleasing.base.reservation.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "t_ol_reservation")
@Data
public class TOLReservation extends DataEntity {

	private String code;

	private String userCode;

	private String userName;

	private String mobile;

	private String email;

	private String merchantCode;

	private String merchantName;

	private String brandCode;

	private String brandName;

	private String brandModality;


	@Column(columnDefinition = "timestamp")
	private Date reserveTime;

	private Integer rentalLength;

	@Column(columnDefinition = "timestamp")
	private Date startDate;

	@Column(columnDefinition = "timestamp")
	private Date endDate;


}
