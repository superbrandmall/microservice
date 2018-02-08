package com.sbm.module.partner.hd.rest.base.domain;

import lombok.Data;

@Data
public class HdBizType {

	private String uuid;

	private String code;

	private String name;

	private String levelId;

	public HdBizType() {
	}

	public HdBizType(String uuid, String code, String name, String levelId) {
		this.uuid = uuid;
		this.code = code;
		this.name = name;
		this.levelId = levelId;
	}
}
