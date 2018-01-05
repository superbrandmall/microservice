package com.sbm.module.template.base.template.repository;

import com.sbm.module.template.base.template.domain.Template;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "template")
public interface ITemplateRepository extends PagingAndSortingRepository<Template, Integer> {
}
