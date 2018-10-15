package com.sbm.module.partner.hl95.rest.sms.client;

import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "hl95", url = "${sms.hl95.url}")
public interface IHl95Client {

	@RequestMapping(value = "", method = RequestMethod.GET)
	String send(@RequestParam(value = "username") String username,
				@RequestParam(value = "password") String password,
				@RequestParam(value = "epid") String epid,
				@RequestParam(value = "phone") String phone,
				@RequestParam(value = "message") String message,
				@RequestParam(value = "linkid") String linkid,
				@RequestParam(value = "subcode") String subcode);

}
