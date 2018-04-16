//package com.sbm.module.common.message.rest.sms.hl95.client;
//
//import org.springframework.cloud.netflix.feign.FeignClient;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@FeignClient(name = "hl95", url = "https://47.95.31.224:8443")
//@RequestMapping("/router/sms")
//public interface IHl95Client {
//
//	@RequestMapping(value = "/send.inf", method = RequestMethod.GET)
//	String send(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password,
//				@RequestParam(value = "epid") String epid, @RequestParam(value = "phone") String phone,
//				@RequestParam(value = "message")String message, @RequestParam(value = "linkid") String linkid,
//				@RequestParam(value = "subcode") String subcode);
//
//}
