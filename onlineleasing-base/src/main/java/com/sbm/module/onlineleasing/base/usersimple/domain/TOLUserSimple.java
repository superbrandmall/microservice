package com.sbm.module.onlineleasing.base.usersimple.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_ol_user_simple")
@Data
public class TOLUserSimple extends DataEntity {

	private String code;

	private String merchantName;

	private String brandName;

	private String modality;

	@Column(columnDefinition = "text")
	private String website;

	@Column(columnDefinition = "text")
	private String file;

	public TOLUserSimple(String code, String merchantName, String brandName, String modality, String website, String file) {
		this.code = code;
		this.merchantName = merchantName;
		this.brandName = brandName;
		this.modality = modality;
		this.website = website;
		this.file = file;
	}

	public TOLUserSimple() {

	}
}
