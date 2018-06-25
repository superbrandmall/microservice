package com.sbm.module.onlineleasing.base.floor.repository;

import com.sbm.module.onlineleasing.base.floor.domain.TOLFloor;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource(path = "floor")
public interface ITOLFloorRepository extends IOLDataRepository<TOLFloor, Integer> {

	TOLFloor findOneByHdUuid(String hdUuid);

	List<TOLFloor> findAllByBuildingCodeInAndDescriptionAndHdState(Collection<String> buildingCodes, String description, String hdState);

	@Query(value = "select DISTINCT description from t_ol_floor where building_code in ?1 and hd_state = 'using'", nativeQuery = true)
	List<String> findAllDescriptionByBuildingCodeIn(Collection<String> buildingCodes);

	List<TOLFloor> findAllByHdState(String hdState);

}
