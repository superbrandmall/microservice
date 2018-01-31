package com.sbm.module.partner.hd.api.biztype.client;

import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.partner.hd.api.biztype.domain.HdBiztype;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(value = "partner-hd")
@RequestMapping("/api/hdBiztype")
public interface IHdBiztypeClient {

	@RequestMapping("/findAllVo")
	@ResponseBody
	JsonContainer<List<HdBiztype>> findAllVo();

}
