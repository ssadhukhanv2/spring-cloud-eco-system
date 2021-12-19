package com.springcloud.ssadhuhanv2.dateutils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class DateUtilsApplication {

	// http://localhost:9001/age?date=26/06/1992
	public static void main(String[] args) {
		SpringApplication.run(DateUtilsApplication.class, args);
	}

}
