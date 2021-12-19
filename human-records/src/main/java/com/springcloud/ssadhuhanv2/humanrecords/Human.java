package com.springcloud.ssadhuhanv2.humanrecords;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Human {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
    String fullName;
    String email;
    String cellPhoneNumber;
    String facebookUrl;
    String linkedInUrl;
    String birthDay;

}
