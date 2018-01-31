package com.sbm.module.partner.hd.api.building.client;

import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.partner.hd.api.floor.domain.HdFloor;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(value = "partner-hd")
@RequestMapping("/api/hdFloor")
public interface IHdBuildingClient {

	@RequestMapping("/findAllVo")
	@ResponseBody
	JsonContainer<List<HdFloor>> findAllVo();

}
