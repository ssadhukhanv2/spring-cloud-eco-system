package com.springcloud.ssadhuhanv2.humanrecords;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Age {
    String stringDate;
    String stringDateFormatter;
    String port;
    Integer ageAsOfToday;
}
