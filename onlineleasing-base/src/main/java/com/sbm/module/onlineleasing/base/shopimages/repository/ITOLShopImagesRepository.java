package com.sbm.module.onlineleasing.base.shopimages.repository;

import com.sbm.module.onlineleasing.base.shopimages.domain.TOLShopImages;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "shopimages")
public interface ITOLShopImagesRepository extends IOLDataRepository<TOLShopImages, Integer> {

	List<TOLShopImages> findAllByCodeOrderByPosition(String code);

}
