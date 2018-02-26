package com.sbm.module.partner.hd.rest.merchant.client;

import com.sbm.module.partner.hd.rest.base.config.HdRestConfig;
import com.sbm.module.partner.hd.rest.base.domain.HdQueryFilter;
import com.sbm.module.partner.hd.rest.base.domain.HdResult;
import com.sbm.module.partner.hd.rest.base.domain.HdResultBody;
import com.sbm.module.partner.hd.rest.merchant.domain.HdMerchant;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "HdMerchant", url = "${hd.url}", configuration = HdRestConfig.class)
@RequestMapping("/rest/onlineleasing/tenant")
public interface IHdMerchantClient {

	@RequestMapping("/query")
	HdResult<HdResultBody<HdMerchant>> query(HdQueryFilter filter);
}
