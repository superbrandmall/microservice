package com.sbm.module.common.message.base.mailsenddetail.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "t_c_mail_send_detail")
@Data
public class TCMailSendDetail extends DataEntity {

	private String code;

	private String templateCode;

	private String sentFrom;

	private String sentTo;

	private String sentCc;

	@Column(columnDefinition = "timestamp")
	private Date sentDate;

	private String subject;

	@Column(columnDefinition = "text")
	private String html;

	private Integer type;

}
