package com.travel_planner_be.travel.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class    UpdateRouteDTO {

    @NotBlank(message = "Please enter routeId")
    private String routeId;

    @NotBlank(message = "Please enter placeId")
    private String placeId;

    private int isDeleteOperation;
}
