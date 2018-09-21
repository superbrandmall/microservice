package com.sbm.module.partner.tianyancha.rest.api736.client;

import com.sbm.module.partner.tianyancha.rest.base.config.TianyanchaConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Api736", url = "${tianyancha.url}", configuration = TianyanchaConfig.class)
@RequestMapping("/services/v4/open")
public interface IApi736Client {

	@RequestMapping("/getCompanyByCode")
	String getCompanyByCode(@RequestParam(value = "code") String code);

}
