package com.sbm.module.onlineleasing.base.mall.repository;

import com.sbm.module.onlineleasing.base.mall.domain.TOLMall;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "mall")
public interface ITOLMallRepository extends IOLDataRepository<TOLMall, Integer> {

	TOLMall findOneByHdUuid(String hdUuid);

	List<TOLMall> findAllByHdState(String hdState);

}
