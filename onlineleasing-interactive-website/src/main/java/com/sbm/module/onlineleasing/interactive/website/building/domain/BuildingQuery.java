package com.sbm.module.onlineleasing.interactive.website.building.domain;

import com.sbm.module.common.data.builder.specification.SpecificationBuilder;
import com.sbm.module.common.data.builder.specification.SpecificationOperate;
import com.sbm.module.onlineleasing.base.building.domain.TOLBuilding;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class BuildingQuery {

	@ApiModelProperty(value = "Mall编号，不传则查询全部")
	private String mallCode;

	public Specification<TOLBuilding> findAll() {
		return new SpecificationBuilder<TOLBuilding>()
				.and("mallCode", mallCode, SpecificationOperate.EQUAL)
				.build();
	}

}
