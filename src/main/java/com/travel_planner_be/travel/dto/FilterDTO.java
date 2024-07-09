package com.travel_planner_be.travel.dto;

import com.travel_planner_be.travel.entity.Participant;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FilterDTO {

    private String departure;

    private String destination;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<String> tags;

    private int participantNumber;

}
