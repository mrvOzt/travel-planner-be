package com.travel_planner_be.travel.entity;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class Route {

    private String userId;
    private double price;
    private DateFormat startDate;
    private DateFormat endDate;
    private List<Place> places;

}
