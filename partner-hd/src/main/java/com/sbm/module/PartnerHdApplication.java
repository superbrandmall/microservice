package com.sbm.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PartnerHdApplication {

	public static void main(String[] args) {
		SpringApplication.run(PartnerHdApplication.class, args);
	}
}
