package com.sbm.module.common.data.builder.specification;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

	private Predicate operate(SpecificationTemplate template, Root<T> root, CriteriaBuilder criteriaBuilder) {
		Predicate predicate;
		switch (template.getOperate()) {
			case LIKE:
				predicate = criteriaBuilder.like(root.get(template.getField()), PERCENT + template.getValue() + PERCENT);
				break;
			default:
				predicate = null;
		}
		return predicate;
	}

}