package com.sbm.module.onlineleasing.base.modality.repository;

import com.sbm.module.onlineleasing.base.modality.domain.TOLModality;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "modality")
public interface ITOLModalityRepository extends IOLDataRepository<TOLModality, Integer> {

	TOLModality findOneByHdUuid(String hdUuid);

	List<TOLModality> findAllByLvAndCodeStartingWith(String lv, String code);

}
