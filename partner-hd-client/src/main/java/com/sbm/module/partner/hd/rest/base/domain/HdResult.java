package com.sbm.module.partner.hd.rest.base.domain;

import lombok.Data;

@Data
public class HdResult<T> {

	private String success;

	private Integer statusCode;

	private String message;

	private T body;

}
