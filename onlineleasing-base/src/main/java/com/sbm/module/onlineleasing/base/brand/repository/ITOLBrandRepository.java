package com.sbm.module.onlineleasing.base.brand.repository;

import com.sbm.module.onlineleasing.base.brand.domain.TOLBrand;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "brand")
public interface ITOLBrandRepository extends IOLDataRepository<TOLBrand, Integer> {

	TOLBrand findOneByHdUuid(String hdUuid);

	TOLBrand findOneByName(String name);

	List<TOLBrand> findAllByNameContaining(String name);

}
