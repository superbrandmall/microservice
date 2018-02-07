package com.sbm.module.onlineleasing.base.modality.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "t_ol_modality")
@Data
public class TOLModality extends DataEntity {

	private String name;

	private String code;

	private String lv;

	private String hdUuid;

	private String jdeModalityId;

	private String hdLevelid;

	private String remark;
}
