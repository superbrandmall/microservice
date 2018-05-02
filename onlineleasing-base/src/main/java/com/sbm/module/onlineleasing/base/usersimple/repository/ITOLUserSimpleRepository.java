package com.sbm.module.onlineleasing.base.usersimple.repository;

import com.sbm.module.onlineleasing.base.usersimple.domain.TOLUserSimple;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "usersimple")
public interface ITOLUserSimpleRepository extends IOLDataRepository<TOLUserSimple, Integer> {


}
