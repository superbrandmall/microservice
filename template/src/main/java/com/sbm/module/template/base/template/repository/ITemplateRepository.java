package com.sbm.module.template.base.template.repository;

import com.sbm.module.template.base.template.domain.Template;
import org.springframework.data.repository.CrudRepository;

public interface ITemplateRepository extends CrudRepository<Template, Integer> {
}
