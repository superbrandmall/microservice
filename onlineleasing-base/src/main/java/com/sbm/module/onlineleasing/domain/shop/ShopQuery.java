package com.sbm.module.onlineleasing.domain.shop;

import com.sbm.module.common.data.builder.specification.SpecificationBuilder;
import com.sbm.module.common.data.builder.specification.SpecificationOperate;
import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class ShopQuery {

	private String code;

	private String unit;

	private String shopName;

	private String mallCode;

	private String hdState;

	private Integer state;

	private Integer isSync;

	public Specification<TOLShop> findAll() {
		return new SpecificationBuilder<TOLShop>()
				.and("code", code, SpecificationOperate.LIKE)
				.and("unit", unit, SpecificationOperate.LIKE)
				.and("shopName", shopName, SpecificationOperate.LIKE)
				.and("mallCode", mallCode, SpecificationOperate.LIKE)
				.and("hdState", hdState, SpecificationOperate.LIKE)
				.and("state", state, SpecificationOperate.EQUAL)
				.and("isSync", isSync, SpecificationOperate.EQUAL)
				.build();
	}

}
