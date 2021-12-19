package com.springcloud.ssadhuhanv2.dateutils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateUtilService {

    /*
     * https://vladmihalcea.com/date-timestamp-jpa-hibernate/
     * */
    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DateConstants.DEFAULT_DATE_FORMAT);

    public Date parseDate(String date) {
        try {
            return DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Date parseDate(String date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
//
//    public static void main(String[] args) {
//        System.out.println(parseDate("26/06/1992"));
//    }
}
