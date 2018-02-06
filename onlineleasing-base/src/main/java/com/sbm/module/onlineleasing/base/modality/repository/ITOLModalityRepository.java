package com.sbm.module.onlineleasing.base.modality.repository;

import com.sbm.module.onlineleasing.base.modality.domain.TOLModality;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "modality")
public interface ITOLModalityRepository extends PagingAndSortingRepository<TOLModality, Integer> {


}
