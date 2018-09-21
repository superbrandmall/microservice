package com.sbm.module.partner.tianyancha.rest.base.domain;

import lombok.Data;

@Data
public class TianyanchaResult<T> {

	private String error_code;

	private String reason;

	private T result;

}
