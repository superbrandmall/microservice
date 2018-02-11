package com.sbm.module.partner.hd.api.building.client;

import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.partner.hd.api.building.domain.HdBuilding;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "partner-hd")
@RequestMapping("/api/hdBuilding")
public interface IHdBuildingClient {

	@RequestMapping("/findAll")
	JsonContainer<List<HdBuilding>> findAll();

}
