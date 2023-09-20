package com.railway.user.reg.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RailwayBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RailwayBookingApplication.class, args);
	}

}
