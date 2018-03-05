package com.sbm.module.onlineleasing.base.building.repository;

import com.sbm.module.onlineleasing.base.building.domain.TOLBuilding;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "building")
public interface ITOLBuildingRepository extends IOLDataRepository<TOLBuilding, Integer> {

	TOLBuilding findOneByHdUuid(String hdUuid);

	List<TOLBuilding> findAllByMallCode(String mallCode);

	@Query(value = "select code from t_ol_building where mall_code = ?1 and hd_state = 'using'", nativeQuery = true)
	List<String> findAllBuildingCodeByMallCode(String mallCode);
}
