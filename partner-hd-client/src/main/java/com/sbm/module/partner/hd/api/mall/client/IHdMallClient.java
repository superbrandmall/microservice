package com.sbm.module.partner.hd.api.mall.client;

import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.partner.hd.api.mall.domain.HdMall;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(value = "partner-hd")
@RequestMapping("/api/hdMall")
public interface IHdMallClient {

	@RequestMapping("/findAllVo")
	JsonContainer<List<HdMall>> findAllVo();

}
