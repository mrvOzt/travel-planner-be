package com.travel_planner_be.travel.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;


public enum PlaceTag {

    HISTORICAL,
    NATURAL,
    POPULER,
    ADVENTURE,
    SPORT,
    WATER_ACTIVTIES,
    ENTERTAINMENT

}
