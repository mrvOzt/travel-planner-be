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

    @NotBlank(message = "Please enter start date of route")
    private LocalDate startDate;

    @NotBlank(message = "Please enter end date of route")
    private LocalDate endDate;

    @NotBlank(message = "Please enter tag info")
    private List<String> tags;

    @NotBlank(message = "Please enter count of participants")
    private int participantNumber;

}
