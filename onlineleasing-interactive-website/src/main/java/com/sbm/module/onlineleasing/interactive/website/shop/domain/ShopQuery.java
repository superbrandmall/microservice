package com.sbm.module.onlineleasing.interactive.website.shop.domain;

import com.sbm.module.common.data.builder.specification.SpecificationBuilder;
import com.sbm.module.common.data.builder.specification.SpecificationOperate;
import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class ShopQuery {

	@ApiModelProperty(value = "Mall编号，不传则查询全部")
	private String mallCode;

	@ApiModelProperty(value = "Building编号，不传则查询全部")
	private String buildingCode;

	@ApiModelProperty(value = "Floor编号，不传则查询全部")
	private String floorCode;

	public Specification<TOLShop> findAll() {
		return new SpecificationBuilder<TOLShop>()
				.and("mallCode", mallCode, SpecificationOperate.EQUAL)
				.and("buildingCode", buildingCode, SpecificationOperate.EQUAL)
				.and("floorCode", floorCode, SpecificationOperate.EQUAL)
				.build();
	}

}
