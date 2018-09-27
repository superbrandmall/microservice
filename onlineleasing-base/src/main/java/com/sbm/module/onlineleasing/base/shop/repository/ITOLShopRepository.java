package com.sbm.module.onlineleasing.base.shop.repository;

import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@RepositoryRestResource(path = "shop")
public interface ITOLShopRepository extends IOLDataRepository<TOLShop, Integer> {

	TOLShop findOneByHdUuid(String hdUuid);

	TOLShop findOneByMallCodeAndHdCodeAndHdState(String mallCode, String hdCode, String hdState);

	List<TOLShop> findAllByFloorCodeInAndShopStateInAndHdState(Collection<String> floorCodes, Collection<Integer> shopStates, String hdState);

	List<TOLShop> findAllByMallCodeAndShopStateInAndHdState(String mallCode, Collection<Integer> shopStates, String hdState);

	@Query(value = "select * from t_ol_shop where mall_code in ?1 and shop_state in (1, 2) and area is not null and hd_state = 'using' and state = 1 and area BETWEEN ?2 and ?3 and sub_type = ?4 ", nativeQuery = true)
	List<TOLShop> findAllBySearchShop(Collection<String> mallCodes, BigDecimal minArea, BigDecimal maxArea, String subType);

	List<TOLShop> findAllByFloorCodeInAndHdState(Collection<String> floorCodes, String hdState);

	List<TOLShop> findAllByHdState(String hdState);

}
