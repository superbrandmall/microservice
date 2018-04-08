package com.sbm.module.onlineleasing.base.shop.repository;

import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource(path = "shop")
public interface ITOLShopRepository extends IOLDataRepository<TOLShop, Integer> {

	TOLShop findOneByHdUuid(String hdUuid);

	TOLShop findOneByMallCodeAndHdCodeAndHdState(String mallCode, String hdCode, String hdState);

	List<TOLShop> findAllByFloorCodeInAndShopStateAndHdState(Collection<String> floorCodes, Integer shopState, String hdState);

	List<TOLShop> findAllByMallCodeAndShopStateAndHdState(String mallCode, Integer shopState, String hdState);

	@Query(value = "select * from t_ol_shop where mall_code in ?1 and shop_state in (1, 2) and area is not null and hd_state = 'using' and state = 1 ", nativeQuery = true)
	List<TOLShop> findAllBySearchShop(Collection<String> mallCodes);

	List<TOLShop> findAllByFloorCodeInAndHdState(Collection<String> floorCodes, String hdState);

}
