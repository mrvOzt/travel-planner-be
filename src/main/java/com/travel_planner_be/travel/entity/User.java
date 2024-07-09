package com.travel_planner_be.travel.entity;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class User {

    @Id
    private String id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private String phoneNumber;

    private List<String> routes;

    private List<String> paymentMethods;

    private String role;

}