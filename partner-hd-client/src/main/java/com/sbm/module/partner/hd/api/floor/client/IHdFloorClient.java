package com.sbm.module.partner.hd.api.floor.client;

import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.partner.hd.api.floor.domain.HdFloor;
import com.sbm.module.partner.hd.api.mall.domain.HdMall;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "partner-hd")
@RequestMapping("/api/hdFloor")
public interface IHdFloorClient {

	@RequestMapping("/findAllVo")
	JsonContainer<List<HdFloor>> findAllVo();

}
