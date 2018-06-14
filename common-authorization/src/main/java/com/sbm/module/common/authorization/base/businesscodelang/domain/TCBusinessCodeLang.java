package com.sbm.module.common.authorization.base.businesscodelang.domain;

import com.sbm.module.common.data.domain.DataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_c_business_code_lang")
@Data
public class TCBusinessCodeLang extends DataEntity {

	private String code;

	private String lang;

	private String customerMessage;

	public TCBusinessCodeLang() {
	}

	public TCBusinessCodeLang(String code, String lang) {
		this.code = code;
		this.lang = lang;
	}

	public TCBusinessCodeLang(String code, String lang, String customerMessage) {
		this.code = code;
		this.lang = lang;
		this.customerMessage = customerMessage;
	}
}
