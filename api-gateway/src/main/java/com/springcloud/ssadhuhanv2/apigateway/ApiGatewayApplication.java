package com.springcloud.ssadhuhanv2.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {

		//http://localhost:8765/date-utils/age?date=26/06/1992
		//http://localhost:8765/human-records/human/1/age
		//http://localhost:8765/human-records/human/1
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
