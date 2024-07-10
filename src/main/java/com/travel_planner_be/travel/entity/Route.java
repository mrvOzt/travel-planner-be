package com.travel_planner_be.travel.entity;

import com.mongodb.lang.Nullable;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Document
public class Route {

    @Id
    private String id;

    private String userId;

    private String userLocation;

    private String routeLocation;

    @Nullable
    private double price;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<String> places;

    private List<Participant> participants;

    @Nullable
    private boolean statusFlag;

}
