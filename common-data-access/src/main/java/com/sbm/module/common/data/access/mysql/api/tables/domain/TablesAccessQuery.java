package com.sbm.module.common.data.access.mysql.api.tables.domain;

import com.sbm.module.common.data.access.mysql.base.tables.domain.Tables;
import com.sbm.module.common.data.builder.specification.SpecificationBuilder;
import com.sbm.module.common.data.builder.specification.SpecificationOperate;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.Specification;

@Data
public class TablesAccessQuery {

	@ApiModelProperty(value = "tableSchema", required = true)
	@NotBlank
	private String tableSchema;

	public Specification<Tables> findAll() {
		return new SpecificationBuilder<Tables>()
				.and("tableSchema", tableSchema, SpecificationOperate.EQUAL)
				.build();
	}

}
