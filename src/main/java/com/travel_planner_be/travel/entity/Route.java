package com.travel_planner_be.travel.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Data
@Document
public class Route {

    private String userId;
    private double price;
    private DateFormat startDate;
    private DateFormat endDate;
    private List<Place> places;

}
