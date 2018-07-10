package com.sbm.module.common.data.builder.specification;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;

@Data
public class SpecificationWrapper<T> implements Specification<T> {

	private static final String PERCENT = "%";

	private List<SpecificationTemplate> templates;

	public SpecificationWrapper(List<SpecificationTemplate> templates) {
		this.templates = templates;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
		// where 1 = 1
		Predicate predicate = criteriaBuilder.and();
		for (SpecificationTemplate template : templates) {
			predicate = join(template, criteriaBuilder, predicate, operate(template, root, criteriaBuilder));
		}
		return predicate;
	}

	/**
	 * 连接
	 *
	 * @param template
	 * @param criteriaBuilder
	 * @param predicates
	 * @return
	 */
	private Predicate join(SpecificationTemplate template, CriteriaBuilder criteriaBuilder, Predicate... predicates) {
		Predicate predicate;
		switch (template.getJoin()) {
			case AND:
				predicate = criteriaBuilder.and(predicates);
				break;
			case OR:
				predicate = criteriaBuilder.or(predicates);
				break;
			default:
				predicate = null;
		}
		return predicate;
	}

	/**
	 * 操作
	 *
	 * @param template
	 * @param root
	 * @param criteriaBuilder
	 * @return
	 */
	private Predicate operate(SpecificationTemplate template, Root<T> root, CriteriaBuilder criteriaBuilder) {
		Predicate predicate;
		switch (template.getOperate()) {
			case EQUAL:
				predicate = criteriaBuilder.equal(get(template, root), template.getValue());
				break;
			case LIKE:
				predicate = criteriaBuilder.like(get(template, root), PERCENT + template.getValue() + PERCENT);
				break;
			default:
				predicate = null;
		}
		return predicate;
	}

	/**
	 * 路径
	 *
	 * @param template
	 * @param root
	 * @return
	 */
	private Path get(SpecificationTemplate template, Root<T> root) {
		return root.get(template.getField());
	}
}