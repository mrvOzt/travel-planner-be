package com.travel_planner_be.travel.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoordinatesDTO {

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;
}
