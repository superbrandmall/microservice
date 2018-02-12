package com.sbm.module.partner.hd.rest.shop.client;

import com.sbm.module.partner.hd.rest.base.config.HdRestConfig;
import com.sbm.module.partner.hd.rest.base.domain.HdQueryFilter;
import com.sbm.module.partner.hd.rest.base.domain.HdResult;
import com.sbm.module.partner.hd.rest.base.domain.HdResultBody;
import com.sbm.module.partner.hd.rest.shop.domain.HdShop;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "HdShop", url = "${hd.url}", configuration = HdRestConfig.class)
@RequestMapping("/rest/onlineleasing/position")
public interface IHdShopClient {

	@RequestMapping("/query")
	HdResult<HdResultBody<HdShop>> query(HdQueryFilter filter);
}
