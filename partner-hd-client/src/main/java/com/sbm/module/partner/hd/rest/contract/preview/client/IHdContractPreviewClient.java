package com.sbm.module.partner.hd.rest.contract.preview.client;

import com.sbm.module.partner.hd.rest.base.config.HdRestConfig;
import com.sbm.module.partner.hd.rest.base.domain.HdResult;
import com.sbm.module.partner.hd.rest.contract.base.domain.HdContract;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "HdContranctPreview", url = "http://10.130.12.22:7280/cre-agency-server", configuration = HdRestConfig.class)
@RequestMapping("/rest/onlineleasing/contract")
public interface IHdContractPreviewClient {

	@RequestMapping("/previewIntention?time=2016-06-13  11:11:11&operator.id=lixiaohong&operator.fullname=李小红&operator.namespace=hd")
	HdResult<String> preview(HdContract hdContract);

}
