package com.sbm.module.onlineleasing.base.shop.repository;

import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource(path = "shop")
public interface ITOLShopRepository extends IOLDataRepository<TOLShop, Integer> {

	TOLShop findOneByHdUuid(String hdUuid);

	List<TOLShop> findAllByFloorCodeInAndShopStateAndHdState(Collection<String> floorCodes, Integer shopState, String hdState);

	List<TOLShop> findAllByMallCodeAndShopStateAndHdState(String mallCode, Integer shopState, String hdState);

}
