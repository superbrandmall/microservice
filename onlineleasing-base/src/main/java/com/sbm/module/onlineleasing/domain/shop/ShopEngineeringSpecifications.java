package com.sbm.module.onlineleasing.domain.shop;

import lombok.Data;

@Data
public class ShopEngineeringSpecifications {

	private String keyword;

	private String name;

	private String title;

	private Integer number;

	private String spec;

	public ShopEngineeringSpecifications(String keyword, String name, String title, Integer number, String spec) {
		this.keyword = keyword;
		this.name = name;
		this.title = title;
		this.number = number;
		this.spec = spec;
	}
}
