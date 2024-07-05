package com.travel_planner_be.travel.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Document
public class Route {

    private String userLocation;
    private String routeLocation;
    private String userId;
    private double price=0;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Place> places;

}
