package com.sbm.module.partner.hd.base.biztype.repository;

import com.sbm.module.common.data.dao.IDataRepository;
import com.sbm.module.partner.hd.base.biztype.domain.Biztype;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "biztype")
public interface IBiztypeRepository extends IDataRepository<Biztype, String> {


}
