package com.sbm.module.partner.hd.base.mall.repository;

import com.sbm.module.common.data.dao.IDataRepository;
import com.sbm.module.partner.hd.base.mall.domain.Mall;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "mall")
public interface IMallRepository extends IDataRepository<Mall, String> {


}
