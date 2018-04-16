package com.sbm.module.common.message.base.smssenddetail.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "t_c_sms_send_detail")
@Data
public class TCSMSSendDetail extends DataEntity {

	private String code;

	private String templateCode;

	private String sentTo;

	@Column(columnDefinition = "timestamp")
	private Date sentDate;

	@Column(columnDefinition = "text")
	private String text;

	private Integer type;

	private String resultCode;

	private String resultMessage;

}
