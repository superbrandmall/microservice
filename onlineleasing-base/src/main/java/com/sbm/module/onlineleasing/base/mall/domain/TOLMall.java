package com.sbm.module.onlineleasing.base.mall.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "t_ol_mall")
@Data
public class TOLMall extends DataEntity {

	private String code;

	private String mallName;

	private String location;

	private BigDecimal grossFloorArea;

	private BigDecimal leasingArea;

	@Column(columnDefinition = "text")
	private String description;

	private String hdUuid;

	private String hdCode;

	private Integer position;

	@Column(columnDefinition = "text")
	private String img;

	private String hdState;

	private String mallNameEng;

	private String locationEng;

	@Column(columnDefinition = "text")
	private String video;

	@Column(columnDefinition = "text")
	private String descriptionEng;

}
