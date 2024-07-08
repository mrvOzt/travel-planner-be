package com.travel_planner_be.travel.dto;

import lombok.Data;

import java.util.List;

@Data
public class FilterDTO {

    private List<String> tags;
    private String city;

}
