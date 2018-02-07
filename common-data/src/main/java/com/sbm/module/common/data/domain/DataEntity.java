package com.sbm.module.common.data.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public class DataEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(columnDefinition = "timestamp", updatable = false)
	private Date created;

	@Column(columnDefinition = "timestamp")
	private Date updated;

	private Integer state;

}
