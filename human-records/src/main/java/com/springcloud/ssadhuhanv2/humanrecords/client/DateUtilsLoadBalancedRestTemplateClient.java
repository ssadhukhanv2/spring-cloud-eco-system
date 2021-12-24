package com.springcloud.ssadhuhanv2.humanrecords.client;

import com.springcloud.ssadhuhanv2.humanrecords.Age;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Component
public class DateUtilsLoadBalancedRestTemplateClient {

    // Here the load balanced rest template is autowired
    @Autowired
    RestTemplate restTemplate;

    public Age getDifferenceWithSystemDate(String date, String formatter) {
        Age age = null;
        String serviceUrl = "http://date-utils/age";
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
        return age;
    }
}
