package com.sbm.module.sync.bi.api.sales.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author bbjohn
 * @date 2019/3/14 16:24
 */
@Data
public class SalesQuery {

	@NotBlank
	private String buildunit;

	@NotBlank
	private String yyyymmdd;

	@NotBlank
	private String brandName;

}
