package com.sbm.module.common.authorization.base.user.repository;

import com.sbm.module.common.authorization.base.user.domain.TCUser;
import com.sbm.module.common.data.repository.IDataRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "user")
public interface ITCUserRepository extends IDataRepository<TCUser, Integer> {

	TCUser findOneByCode(String code);

	@Query(value = "select * from t_c_user where email = ?1 or mobile = ?1", nativeQuery = true)
	TCUser findOneByUsername(String username);

	TCUser findOneByEmail(String email);

	TCUser findOneByMobile(String mobile);

}
