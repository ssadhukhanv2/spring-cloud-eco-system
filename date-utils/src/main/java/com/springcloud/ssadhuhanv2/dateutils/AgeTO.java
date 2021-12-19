package com.springcloud.ssadhuhanv2.dateutils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgeTO {
    String stringDate;
    String stringDateFormatter;
    String port;
    Integer ageAsOfToday;
}
