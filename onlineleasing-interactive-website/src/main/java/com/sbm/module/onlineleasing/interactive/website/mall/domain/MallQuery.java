package com.sbm.module.onlineleasing.interactive.website.mall.domain;

import com.sbm.module.common.data.builder.specification.SpecificationBuilder;
import com.sbm.module.common.data.builder.specification.SpecificationOperate;
import com.sbm.module.onlineleasing.base.mall.domain.TOLMall;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class MallQuery {

	private String hdCode;

	public Specification<TOLMall> findAll() {
		return new SpecificationBuilder<TOLMall>()
				.and("hdCode", hdCode, SpecificationOperate.EQUAL)
				.build();
	}

}
