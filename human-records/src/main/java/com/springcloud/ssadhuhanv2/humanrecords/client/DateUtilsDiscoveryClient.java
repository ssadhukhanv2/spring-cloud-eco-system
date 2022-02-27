package com.springcloud.ssadhuhanv2.humanrecords.client;

import com.springcloud.ssadhuhanv2.humanrecords.Age;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class DateUtilsDiscoveryClient {


    /*
    * The Spring Discovery Client offers the lowest level of access to
    * the Load Balancer and the services registered within it.
    * Using the Discovery Client, you can query for all the services
    * registered with the Spring Cloud Load Balancer client and their corresponding URLs.
    * */
    @Autowired
    private DiscoveryClient discoveryClient;


    public Age getDifferenceWithSystemDate(String date, String formatter) {

        // Here DiscoveryClient is used to find the
        // list of running instances for date-utils
        // Then make service call to the first instance

        RestTemplate restTemplate = new RestTemplate();
        Age age = null;

        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("date-utils");
        log.info("List of \"date-utils\" instances", serviceInstanceList.toString());

        if (null != serviceInstanceList && serviceInstanceList.size() > 0) {
            //http://localhost:9001/age?date=26/06/1992
            String serviceUrl = String.format("%s/age", serviceInstanceList.get(0).getUri().toString());

            // how to send request param in resttemplate
            //https://stackoverflow.com/questions/8297215/spring-resttemplate-get-with-parameters
            String uriTemplate = UriComponentsBuilder.fromHttpUrl(serviceUrl).queryParam("date", "{birthDay}").encode().toUriString();
            Map<String, String> params = new HashMap<>();
            params.put("birthDay", date);
            if (null != formatter && !formatter.isEmpty()) {
                //http://localhost:9001/age?date=1992/06/26&formatter=YYYY/MM/DD
                uriTemplate = UriComponentsBuilder.fromHttpUrl(serviceUrl).queryParam("date", "{birthDay}").queryParam("formatter", "{formatOfBirthDay}").encode().toUriString();
                params.put("formatOfBirthDay", formatter);
            }
            age = restTemplate.getForObject(uriTemplate, Age.class, params);
        }
        return age;
    }
}
