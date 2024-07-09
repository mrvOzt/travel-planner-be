package com.travel_planner_be.travel.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Place {

    @Id
    private String id;
    private String place_name;
    private double latitude;
    private double longitude;
    private String imageUrl;
    private String country;
    private String city;
    private String district;
    private String duration;
    private double price;
    private String type;
    private List<String> tag;
    public String genderPreference;
}
