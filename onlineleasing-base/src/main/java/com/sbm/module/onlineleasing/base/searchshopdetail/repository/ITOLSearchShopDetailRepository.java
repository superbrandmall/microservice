package com.sbm.module.onlineleasing.base.searchshopdetail.repository;

import com.sbm.module.onlineleasing.base.searchshopdetail.domain.TOLSearchShopDetail;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "searchshopdetail")
public interface ITOLSearchShopDetailRepository extends IOLDataRepository<TOLSearchShopDetail, Integer> {

	Page<TOLSearchShopDetail> findAllByUserCodeOrderByUpdatedDesc(String userCode, Pageable pageable);

}
