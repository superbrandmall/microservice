package com.sbm.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

//@SpringBootApplication(scanBasePackages = "com.sbm.module.common.data.access.mysql")
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class CommonDataAccessApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonDataAccessApplication.class, args);
	}
}
