package com.sbm.module.common.message.base.template.repository;

import com.sbm.module.common.data.repository.IDataRepository;
import com.sbm.module.common.message.base.template.domain.TCTemplate;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "template")
public interface ITCTemplateRepository extends IDataRepository<TCTemplate, Integer> {


}
