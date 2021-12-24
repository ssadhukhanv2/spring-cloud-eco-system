package com.springcloud.ssadhuhanv2.humanrecords.service;

import com.springcloud.ssadhuhanv2.humanrecords.Age;
import com.springcloud.ssadhuhanv2.humanrecords.Human;
import com.springcloud.ssadhuhanv2.humanrecords.HumanRecordsClientConstant;
import com.springcloud.ssadhuhanv2.humanrecords.HumanRepo;
import com.springcloud.ssadhuhanv2.humanrecords.client.DateUtilsDiscoveryClient;
import com.springcloud.ssadhuhanv2.humanrecords.client.DateUtilsFeignClient;
import com.springcloud.ssadhuhanv2.humanrecords.client.DateUtilsLoadBalancedRestTemplateClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HumanService {


    @Autowired
    HumanRepo humanRepo;

    // Proxy Feign Client
    @Autowired
    DateUtilsFeignClient dateUtilsFeignClient;


    //Discovery Client uses Rest Template
    @Autowired
    DateUtilsDiscoveryClient dateUtilsDiscoveryClient;

    //Load Balanced RestTemplate Client
    @Autowired
    DateUtilsLoadBalancedRestTemplateClient dateUtilsLoadBalancedRestTemplateClient;

    public Human getHumanById(Long id) {
        return humanRepo.findById(id).get();
    }


    public List<Human> getAllHumans() {
        return humanRepo.findAll();
    }

    public Age getHumanAgeById(Long id, String clientType) {
        log.info("Id of Human: {}", id);
        Human human = humanRepo.findById(id).get();
        log.info("Human Found: {}", human);

        Age age = null;
        switch (clientType) {
            case HumanRecordsClientConstant.FEIGN:
                log.info("Using Feign Client");
                age = dateUtilsFeignClient.getDifferenceWithSystemDate(human.getBirthDay(), null);
                break;
            case HumanRecordsClientConstant.LOAD_BALANCED_REST:
                log.info("Using Load Balanced(Spring Cloud Load Balancer) RestTemplate");
                age = dateUtilsLoadBalancedRestTemplateClient.getDifferenceWithSystemDate(human.getBirthDay(), null);
                break;
            case HumanRecordsClientConstant.DISCOVERY:
                log.info("Using load DiscoverClient to manually send traffic to specific node");
                age = dateUtilsDiscoveryClient.getDifferenceWithSystemDate(human.getBirthDay(), null);
                break;
            default:
                log.info("Using Load Balanced(Spring Cloud Load Balancer) RestTemplate");
                age = dateUtilsLoadBalancedRestTemplateClient.getDifferenceWithSystemDate(human.getBirthDay(), null);
                break;
        }

        return age;
    }

}
