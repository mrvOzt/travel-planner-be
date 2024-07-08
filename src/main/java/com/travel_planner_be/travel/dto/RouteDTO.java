package com.travel_planner_be.travel.dto;

import com.travel_planner_be.travel.entity.Route;
import lombok.Data;

@Data
public class RouteDTO {

    private Route route;
    private String userId;

}
