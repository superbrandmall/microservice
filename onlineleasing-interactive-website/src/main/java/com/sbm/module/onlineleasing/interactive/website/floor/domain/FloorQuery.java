package com.sbm.module.onlineleasing.interactive.website.floor.domain;

import com.sbm.module.common.data.builder.specification.SpecificationBuilder;
import com.sbm.module.common.data.builder.specification.SpecificationOperate;
import com.sbm.module.onlineleasing.base.floor.domain.TOLFloor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class FloorQuery {

	@ApiModelProperty(value = "Building编号，不传则查询全部")
	private String buildingCode;

	public Specification<TOLFloor> findAll() {
		return new SpecificationBuilder<TOLFloor>()
				.and("buildingCode", buildingCode, SpecificationOperate.EQUAL)
				.build();
	}

}
