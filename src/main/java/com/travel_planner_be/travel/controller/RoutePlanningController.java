package com.travel_planner_be.travel.controller;

import com.travel_planner_be.travel.entity.Place;
import com.travel_planner_be.travel.service.RoutePlanningService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@AllArgsConstructor
public class RoutePlanningController {

    
    private RoutePlanningService routePlanningService;

    @PostMapping("/createRoutePlanning")
    public List<Place> createRoutePlanning(@RequestBody List<String> types){
        return routePlanningService.createRoutePlanning(types);
    }

}
