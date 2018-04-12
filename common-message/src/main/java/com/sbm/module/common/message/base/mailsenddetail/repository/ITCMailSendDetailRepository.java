package com.sbm.module.common.message.base.mailsenddetail.repository;

import com.sbm.module.common.data.repository.IDataRepository;
import com.sbm.module.common.message.base.mailsenddetail.domain.TCMailSendDetail;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "mailsenddetail")
public interface ITCMailSendDetailRepository extends IDataRepository<TCMailSendDetail, Integer> {


}
