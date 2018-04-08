package com.sbm.module.onlineleasing.base.bid.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "t_ol_bid")
@Data
public class TOLBid extends DataEntity {

	private String code;

	private String userCode;

	private String merchantCode;

	private String shopCode;

	private String brandCode;

	@Column(columnDefinition = "timestamp")
	private Date startDate;

	@Column(columnDefinition = "timestamp")
	private Date endDate;

	private Integer contractLength;

	private Integer leaseType;

	private Integer accType;

	private Integer rentMethod;

	private Integer compareFrequency;

	@Column(columnDefinition = "decimal(15,2)")
	private BigDecimal gurantee;

	@Column(columnDefinition = "decimal(15,2)")
	private BigDecimal maintenanceDuringDecoration;

	@Column(columnDefinition = "decimal(15,2)")
	private BigDecimal maintenanceAfterDecoration;

	@Column(columnDefinition = "decimal(15,2)")
	private BigDecimal target;

	@Column(columnDefinition = "decimal(15,2)")
	private BigDecimal publicDeposit;

	private Integer promotion;

	private Integer freeDays;

	private Integer promotionKind;

	@Column(columnDefinition = "decimal(15,2)")
	private BigDecimal promotionBudget;

	@Column(columnDefinition = "timestamp")
	private Date opening;

	private Integer cashierMode;

	private Integer businessHours;

	private String extraBusinessHour_1;

	private String extraBusinessHour_2;

	@Column(columnDefinition = "text")
	private String others;

	private Integer extendedDecoration;

	private Integer businessFreePeriod;

	private Integer freeAdsNo;

	@Column(columnDefinition = "decimal(15,2)")
	private BigDecimal practiceRateReached;

	@Column(columnDefinition = "text")
	private String transferCondition;

	@Column(columnDefinition = "text")
	private String maintenanceCompensate;

	@Column(columnDefinition = "text")
	private String exclusiveCondition;

	@Column(columnDefinition = "text")
	private String bindingCondition;

	/**
	 * 处理状态
	 */
	private Integer processState;

	@Column(columnDefinition = "text")
	private String legalSuggest;

	@Column(columnDefinition = "text")
	private String businessSuggest;

	/**
	 * 标准/非标
	 */
	private Integer isStandard;

	/**
	 * 审批结果
	 */
	private Integer isApprove;

	/**
	 * 是否生效
	 */
	private Integer isEffect;

	/**
	 * 过期日期
	 */
	@Column(columnDefinition = "timestamp")
	private Date expireDate;

	private String billNumber;

}
