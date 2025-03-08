package com.lloms.outlet_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OutletServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OutletServiceApplication.class, args);
	}

}
