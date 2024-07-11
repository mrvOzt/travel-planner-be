package com.travel_planner_be.travel.dto;

import com.travel_planner_be.travel.entity.Participant;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FilterDTO {

    @NotBlank(message = "Please enter departure information")
    private String departure;

    @NotBlank(message = "Please enter destination information")
    private String destination;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<String> tags;

    private int participantNumber;

}
