package com.sbm.module.onlineleasing.base.reservation.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "t_ol_reservation")
@Data
public class TOLReservation extends DataEntity {

	private String code;

	private String mallCode;

	private String mallName;

	private String floorCode;

	private String floorName;

	private String shopCode;

	private String unit;

	@Column(columnDefinition = "decimal(15,2)")
	private BigDecimal area;

	private String modality;


	private String userCode;

	private String merchantCode;

	private String merchantName;


	@Column(columnDefinition = "decimal(15,2)")
	private BigDecimal deadRent;

	@Column(columnDefinition = "decimal(15,2)")
	private BigDecimal floatingRentalRate;

	@Column(columnDefinition = "decimal(15,2)")
	private BigDecimal promotionBudget;

	@Column(columnDefinition = "decimal(15,2)")
	private BigDecimal maintenanceDuringDecoration;

	@Column(columnDefinition = "decimal(15,2)")
	private BigDecimal maintenanceAfterDecoration;

	@Column(columnDefinition = "decimal(15,2)")
	private BigDecimal gurantee;


	private String brandCode;

	private String brandName;

	@Column(columnDefinition = "timestamp")
	private Date reserveTime;

	private Integer rentalLength;

	@Column(columnDefinition = "timestamp")
	private Date startDate;

	@Column(columnDefinition = "timestamp")
	private Date endDate;


}
