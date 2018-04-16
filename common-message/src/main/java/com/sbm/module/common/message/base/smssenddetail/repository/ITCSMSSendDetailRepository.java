package com.sbm.module.common.message.base.smssenddetail.repository;

import com.sbm.module.common.data.repository.IDataRepository;
import com.sbm.module.common.message.base.smssenddetail.domain.TCSMSSendDetail;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "smssenddetail")
public interface ITCSMSSendDetailRepository extends IDataRepository<TCSMSSendDetail, Integer> {


}
