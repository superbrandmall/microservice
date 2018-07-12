package com.sbm.module.onlineleasing.domain.floor;

import com.sbm.module.common.data.builder.specification.SpecificationBuilder;
import com.sbm.module.common.data.builder.specification.SpecificationOperate;
import com.sbm.module.onlineleasing.base.floor.domain.TOLFloor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class FloorQuery {

	private String code;

	private String hdState;

	private Integer state;

	public Specification<TOLFloor> findAll() {
		return new SpecificationBuilder<TOLFloor>()
				.and("code", code, SpecificationOperate.LIKE)
				.and("hdState", hdState, SpecificationOperate.LIKE)
				.and("state", state, SpecificationOperate.EQUAL)
				.build();
	}

}
