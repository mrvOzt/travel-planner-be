package com.travel_planner_be.travel.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class UpdateRouteDTO {

    private String routeId;

    private String placeId;

    private int isDeleteOperation;
}
