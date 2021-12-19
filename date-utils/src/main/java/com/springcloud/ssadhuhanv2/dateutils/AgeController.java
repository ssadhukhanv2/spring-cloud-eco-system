package com.springcloud.ssadhuhanv2.dateutils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class AgeController {

    @Autowired
    DateUtilService dateUtilService;

    @Autowired
    Environment environment;

    @GetMapping("/age")
    public @ResponseBody
    AgeTO getDifferenceWithSystemDate(@RequestParam(name = "date") String stringDate, @RequestParam(name = "dateFormat", required = false) String formatter) {
        log.info("stringDate: {} formatter {}", stringDate, formatter);

        Date parsedDate = null;
        AgeTO ageTO = new AgeTO();
        ageTO.setStringDate(stringDate);
        if (null == formatter || formatter.isEmpty()) {
            ageTO.setStringDateFormatter(DateConstants.DEFAULT_DATE_FORMAT);
            parsedDate = dateUtilService.parseDate(stringDate);
        } else {
            ageTO.setStringDateFormatter(formatter);
            parsedDate = dateUtilService.parseDate(stringDate, formatter);
        }

        log.info("Parsed Date: {}", parsedDate);
        //Find difference between two dates
        //https://www.baeldung.com/java-date-difference
        Date currentDate = new Date();
        long diffInMillis = Math.abs(parsedDate.getTime() - currentDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        log.info("Parsed Date: {}\nCurrent Date: {}\nDifference in Milliseconds: {}\nDifference in Days: {}", parsedDate, currentDate, diffInMillis, diff);

        // Get Current Date and Time in Java
        // https://stackoverflow.com/questions/30312058/spring-boot-how-to-get-the-running-port
        ageTO.setPort(environment.getProperty("local.server.port"));
        ageTO.setAgeAsOfToday((int) diff / 365);

        log.info("Age: {}", ageTO);
        return ageTO;
    }
}
