package com.sbm.module.partner.hd.rest.brand.client;

import com.sbm.module.partner.hd.rest.base.config.HdRestConfig;
import com.sbm.module.partner.hd.rest.base.domain.HdQueryFilter;
import com.sbm.module.partner.hd.rest.base.domain.HdResult;
import com.sbm.module.partner.hd.rest.base.domain.HdResultBody;
import com.sbm.module.partner.hd.rest.brand.domain.HdBrand;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "HdBrand", url = "${hd.url}", configuration = HdRestConfig.class)
@RequestMapping("/rest/onlineleasing/brand")
public interface IHdBrandClient {

	@RequestMapping("/query")
	HdResult<HdResultBody<HdBrand>> query(HdQueryFilter filter);

	@RequestMapping("/")
	HdResult<HdBrand> save(HdBrand hdBrand);
}
