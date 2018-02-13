package com.sbm.module.onlineleasing.base.shopengineeringimages.repository;

import com.sbm.module.onlineleasing.base.shopengineeringimages.domain.TOLShopEngineeringImages;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "shopengineeringimages")
public interface ITOLShopEngineeringImagesRepository extends IOLDataRepository<TOLShopEngineeringImages, Integer> {

}
