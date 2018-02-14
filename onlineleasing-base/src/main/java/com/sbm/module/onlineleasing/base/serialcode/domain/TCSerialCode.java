package com.sbm.module.onlineleasing.base.serialcode.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Table(name = "t_c_serial_code")
@Data
public class TCSerialCode extends DataEntity {

	private String serialGroup;

	private Integer serialNum;

	@Column(columnDefinition = "timestamp")
	private Date serialDate;

	private String remark;

	@Transient
	private String next;
}
