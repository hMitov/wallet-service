package com.paymentsystem.rest.dto;

import com.domain.entity.Gender;
import lombok.Data;

@Data
public class CustomerData {

    private String firstName;

    private String lastName;

    private String age;

    private Gender gender;

    private String country;

    private String city;

    private String nameOfStreet;

    private String numberOfBuilding;

    private String postCode;

    private String telephone;

    private String email;

}
