package com.travel_planner_be.travel.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Participant {

    private String name;

    private String surname;

    private String email;

    private String gender;
}
