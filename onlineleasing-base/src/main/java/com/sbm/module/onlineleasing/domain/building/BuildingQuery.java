package com.sbm.module.onlineleasing.domain.building;

import com.sbm.module.common.data.builder.specification.SpecificationBuilder;
import com.sbm.module.common.data.builder.specification.SpecificationOperate;
import com.sbm.module.onlineleasing.base.building.domain.TOLBuilding;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class BuildingQuery {

	private String code;

	private String hdState;

	private Integer state;

	public Specification<TOLBuilding> findAll() {
		return new SpecificationBuilder<TOLBuilding>()
				.and("code", code, SpecificationOperate.LIKE)
				.and("hdState", hdState, SpecificationOperate.LIKE)
				.and("state", state, SpecificationOperate.EQUAL)
				.build();
	}

}
