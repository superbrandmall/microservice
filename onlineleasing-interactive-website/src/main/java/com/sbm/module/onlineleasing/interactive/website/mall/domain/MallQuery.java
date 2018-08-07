package com.sbm.module.onlineleasing.interactive.website.mall.domain;

import com.sbm.module.common.data.builder.specification.SpecificationBuilder;
import com.sbm.module.common.data.builder.specification.SpecificationOperate;
import com.sbm.module.onlineleasing.base.mall.domain.TOLMall;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
@ApiModel(value = "查询条件")
public class MallQuery {

	@ApiModelProperty(value = "业务编号，不传则查询全部")
	private String hdCode;

	public Specification<TOLMall> findAll() {
		return new SpecificationBuilder<TOLMall>()
				.and("hdCode", hdCode, SpecificationOperate.EQUAL)
				.build();
	}

}
