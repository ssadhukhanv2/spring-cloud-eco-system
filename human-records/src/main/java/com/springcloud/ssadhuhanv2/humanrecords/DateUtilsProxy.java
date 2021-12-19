package com.springcloud.ssadhuhanv2.humanrecords;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//@FeignClient(name = "date-utils", url = "localhost:9001")
// url is omitted as Eureka Server is BEING used here
// in case multiple instances of date-utils are running,
// they are automatically load balanced by feign
// using Spring Cloud Load Balancer
@FeignClient(name = "date-utils")
public interface DateUtilsProxy {
    @GetMapping("/age")
    public @ResponseBody
    Age getDifferenceWithSystemDate(@RequestParam(name = "date") String stringDate,
                                    @RequestParam(name = "dateFormat", required = false) String formatter);
}
