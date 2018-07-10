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

	//	protected void process(List<Predicate> predicates) {
//		if (StringUtils.isNotBlank(code)) {
//			predicates.add(criteriaBuilder.like(root.get("code").as(String.class), "%" + code + "%"));
//		}
//		if (StringUtils.isNotBlank(unit)) {
//			predicates.add(criteriaBuilder.like(root.get("unit").as(String.class), "%" + unit + "%"));
//		}
//		if (StringUtils.isNotBlank(shopName)) {
//			predicates.add(criteriaBuilder.like(root.get("shopName").as(String.class), "%" + shopName + "%"));
//		}
//		if (StringUtils.isNotBlank(mallCode)) {
//			predicates.add(criteriaBuilder.like(root.get("mallCode").as(String.class), "%" + mallCode + "%"));
//		}
//		if (StringUtils.isNotBlank(hdState)) {
//			predicates.add(criteriaBuilder.like(root.get("hdState").as(String.class), "%" + hdState + "%"));
//		}
//	}
//
	public Specification<TOLShop> findAll() {
		return new SpecificationBuilder<TOLShop>()
				.and("code", code, SpecificationOperate.LIKE)
				.and("unit", unit, SpecificationOperate.LIKE)
				.and("shopName", shopName, SpecificationOperate.LIKE)
				.and("mallCode", mallCode, SpecificationOperate.LIKE)
				.and("hdState", hdState, SpecificationOperate.LIKE)
				.build();
	}
//
//	public Specification<T> toPredicate() {
//		return (root, criteriaQuery, criteriaBuilder) -> {
//
//			return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
//		};
//	}
}
