package com.travel_planner_be.travel.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDTO {
    private String name;
    private String surname;
    private String username;
    private String phone_number;
    private String password;
}