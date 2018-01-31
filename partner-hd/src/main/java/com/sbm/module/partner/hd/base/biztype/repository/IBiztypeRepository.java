package com.sbm.module.partner.hd.base.biztype.repository;

import com.sbm.module.partner.hd.base.biztype.domain.Biztype;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "biztype")
public interface IBiztypeRepository extends PagingAndSortingRepository<Biztype, String> {


}
