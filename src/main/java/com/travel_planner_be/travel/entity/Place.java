package com.travel_planner_be.travel.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Place {

    private String place_name;
    private double latitude;
    private double longtitude;
    private String country;
    private String city;
    private String district;
    private String duration;
    private String type;
    private List<PlaceTag> tag;
}
