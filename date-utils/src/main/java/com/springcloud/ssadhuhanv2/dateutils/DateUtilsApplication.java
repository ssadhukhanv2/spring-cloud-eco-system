package com.springcloud.ssadhuhanv2.dateutils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class DateUtilsApplication {

	// http://localhost:9001/age?date=26/06/1992
	// http://localhost:9001/age?date=1992/06/26&formatter=YYYY/MM/DD
	public static void main(String[] args) {
		SpringApplication.run(DateUtilsApplication.class, args);
	}

}
