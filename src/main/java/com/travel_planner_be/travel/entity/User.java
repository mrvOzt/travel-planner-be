package com.travel_planner_be.travel.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class User {

    @Id
    private String id;
    private String name;
    private String surname;
    private String phone_number;
    private String email;
    private List<Route> routes;

}
