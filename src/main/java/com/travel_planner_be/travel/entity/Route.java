package com.travel_planner_be.travel.entity;

import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document
public class Route {

    @Id
    private String id;

    @NotBlank(message = "Please enter a valid userId")
    private String userId;

    @NotBlank(message = "Please enter a valid departure")
    private String departure;

    @NotBlank(message = "Please enter a valid destination")
    private String destination;

    private double price;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<String> places;

    private List<Participant> participants;

    @Nullable
    private boolean statusFlag;

}
