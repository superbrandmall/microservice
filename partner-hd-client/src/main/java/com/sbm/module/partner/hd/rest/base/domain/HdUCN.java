package com.sbm.module.partner.hd.rest.base.domain;

import lombok.Data;

@Data
public class HdUCN {

	private String uuid;

	private String code;

	private String name;

	public HdUCN() {
	}

	public HdUCN(String uuid, String code, String name) {
		this.uuid = uuid;
		this.code = code;
		this.name = name;
	}
}
