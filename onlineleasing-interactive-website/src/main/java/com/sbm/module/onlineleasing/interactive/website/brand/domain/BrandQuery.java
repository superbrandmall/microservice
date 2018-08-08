package com.sbm.module.onlineleasing.interactive.website.brand.domain;

import com.sbm.module.common.data.builder.specification.SpecificationBuilder;
import com.sbm.module.onlineleasing.base.brand.domain.TOLBrand;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class BrandQuery {

	public Specification<TOLBrand> findAll() {
		return new SpecificationBuilder<TOLBrand>()
				.build();
	}

}
