package com.sbm.module.partner.hl95.rest.sms.client;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.text.MessageFormat;

@Component
public class Hl95RestTemplateClient {

	@Autowired
	private RestTemplate restTemplate;

	public static final String BASE_URL = "https://47.95.31.224:8443/router/sms/send.inf";
	private static final String METHOD = "?username={0}&password={1}&epid={2}&phone={3}&message={4}&linkid={5}&subcode={6}";
	public static final String ENCODE = "gb2312";

	@SneakyThrows
	public String send(String username, String password, String epid, String phone, String message, String linkid, String subcode) {
		return restTemplate.getForObject(MessageFormat.format(BASE_URL + METHOD, username, password, epid, phone,
				URLEncoder.encode(message, ENCODE), linkid, subcode), String.class);
	}

}
