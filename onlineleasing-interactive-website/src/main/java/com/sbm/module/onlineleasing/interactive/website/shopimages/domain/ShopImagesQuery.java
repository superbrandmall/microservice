package com.sbm.module.onlineleasing.interactive.website.shopimages.domain;

import com.sbm.module.common.data.builder.specification.SpecificationBuilder;
import com.sbm.module.common.data.builder.specification.SpecificationOperate;
import com.sbm.module.onlineleasing.base.shopimages.domain.TOLShopImages;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class ShopImagesQuery {

	@ApiModelProperty(value = "Shop编号，不传则查询全部")
	private String shopCode;

	public Specification<TOLShopImages> findAll() {
		return new SpecificationBuilder<TOLShopImages>()
				.and("code", shopCode, SpecificationOperate.EQUAL)
				.build();
	}

}
