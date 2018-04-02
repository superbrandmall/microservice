package com.sbm.module.onlineleasing.base.myfavourite.repository;

import com.sbm.module.common.data.repository.IDataRepository;
import com.sbm.module.onlineleasing.base.myfavourite.domain.TOLMyFavourite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "myfavourite")
public interface ITOLMyFavouriteRepository extends IDataRepository<TOLMyFavourite, Integer> {

	Page<TOLMyFavourite> findByUserCode(String code, Pageable pageable);

	TOLMyFavourite findOneByUserCodeAndShopCode(String userCode, String shopCode);

}
