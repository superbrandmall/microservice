package com.sbm.module.onlineleasing.domain.brand;

import com.sbm.module.common.data.builder.specification.SpecificationBuilder;
import com.sbm.module.common.data.builder.specification.SpecificationOperate;
import com.sbm.module.onlineleasing.base.brand.domain.TOLBrand;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class BrandQuery {

	private String code;

	private String hdState;

	private Integer state;

	public Specification<TOLBrand> findAll() {
		return new SpecificationBuilder<TOLBrand>()
				.and("code", code, SpecificationOperate.LIKE)
				.and("hdState", hdState, SpecificationOperate.LIKE)
				.and("state", state, SpecificationOperate.EQUAL)
				.build();
	}

}
