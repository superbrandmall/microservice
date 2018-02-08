package com.sbm.module.partner.hd.rest.contract.client;

import com.sbm.module.partner.hd.rest.base.config.HdRestConfig;
import com.sbm.module.partner.hd.rest.base.domain.HdResult;
import com.sbm.module.partner.hd.rest.contract.domain.HdContract;
import com.sbm.module.partner.hd.rest.contract.domain.HdContractNonStandardResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "HdContranctPreview", url = "http://10.130.12.22:7280/cre-agency-server", configuration = HdRestConfig.class)
@RequestMapping("/rest/onlineleasing/contract")
public interface IHdContractClient {

	@RequestMapping("/previewIntention?time=2018-02-08  11:11:11&operator.id=lixiaohong&operator.fullname=李小红&operator.namespace=hd")
	HdResult<String> preview(HdContract hdContract);

	@RequestMapping("/saveNewIntentionRequest?time=2018-02-08  10:55:55&operator.id=lixiaohong&operator.fullname=李小红&operator.namespace=hd")
	HdResult<HdContractNonStandardResult> nonstandardSubmit(List<HdContract> hdContracts);

}
