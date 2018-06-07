package com.sbm.module.onlineleasing.domain.shop;

import com.sbm.module.common.data.domain.QueryEntity;
import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import lombok.Data;
import org.springframework.data.domain.ExampleMatcher;

@Data
public class ShopQuery extends QueryEntity<TOLShop> {

	private String code;

	private String unit;

	private String shopName;

	private String mallName;

	@Override
	public TOLShop po() {
		TOLShop po = new TOLShop();
		po.setCode(code);
		po.setUnit(unit);
		po.setShopName(shopName);
		po.setMallName(mallName);
		return po;
	}

	@Override
	public ExampleMatcher matcher() {
		return ExampleMatcher.matching()
				.withMatcher("code", e -> e.contains())
				.withMatcher("unit", e -> e.contains())
				.withMatcher("shopName", e -> e.contains())
				.withMatcher("mallName", e -> e.contains());
	}

}