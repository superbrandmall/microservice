package com.sbm.module.onlineleasing.base.shopimages.repository;

import com.sbm.module.onlineleasing.base.shopimages.domain.TOLShopImages;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "shopimages")
public interface ITOLShopImagesRepository extends IOLDataRepository<TOLShopImages, Integer> {

}
