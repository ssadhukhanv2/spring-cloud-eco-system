package com.springcloud.ssadhuhanv2.humanrecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients
@SpringBootApplication
public class HumanRecordsApplication implements CommandLineRunner {


    @Autowired
    HumanRepo humanRepo;

    public static void main(String[] args) {
        SpringApplication.run(HumanRecordsApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void run(String... args) throws Exception {
        Human human = new Human();
        human.setBirthDay("09/12/2000");
        human.setName("xxxx");
        human.setEmail("xxxx@gmail.com");
        human.setFullName("xxxx xxxx");
        human.setCellPhoneNumber("+91 9999999999");
        human.setFacebookUrl("xxxx@facebook.com");
        human.setFacebookUrl("xxxx@linkedIn.com");
        humanRepo.save(human);
    }
}
