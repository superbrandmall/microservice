package com.sbm.module.onlineleasing.base.malltraffic.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_ol_mall_traffic")
@Data
public class TOLMallTraffic extends DataEntity {

	private String code;

	private String type;

	private String text;

	private String remark;

}
