package com.sbm.module.template.base.template.repository;

import com.sbm.module.common.data.repository.IDataRepository;
import com.sbm.module.template.base.template.domain.Template;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "template")
public interface ITemplateRepository extends IDataRepository<Template, Integer> {


}
