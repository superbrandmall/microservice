package com.sbm.module.onlineleasing.domain.mall;

import com.sbm.module.common.data.builder.specification.SpecificationBuilder;
import com.sbm.module.common.data.builder.specification.SpecificationOperate;
import com.sbm.module.onlineleasing.base.mall.domain.TOLMall;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class MallQuery {

	private String code;

	private String mallName;

	private String hdState;

	private Integer state;

	public Specification<TOLMall> findAll() {
		return new SpecificationBuilder<TOLMall>()
				.and("code", code, SpecificationOperate.LIKE)
				.and("mallName", mallName, SpecificationOperate.LIKE)
				.and("hdState", hdState, SpecificationOperate.LIKE)
				.and("state", state, SpecificationOperate.EQUAL)
				.build();
	}

}
