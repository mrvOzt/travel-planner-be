package com.travel_planner_be.travel.controller;


import com.travel_planner_be.travel.dto.SignupDTO;
import com.travel_planner_be.travel.entity.Place;
import com.travel_planner_be.travel.entity.Route;
import com.travel_planner_be.travel.repository.UserRepository;
import com.travel_planner_be.travel.service.RouteService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("api/route/")
public class RouteController {

    @Autowired
    private RouteService routeService;



    @PostMapping(value ="/saveRoute")
    public Route saveRoute(@RequestBody Route route) {
        return routeService.saveRoute(route);
    }

    @GetMapping(value = "/getRoutes/{userId}")
    public List<Route> getUserRoutes(@PathVariable String userId) {
        return routeService.getRouteByUserId(userId);
    }

    @GetMapping(value = "/getRoutePlaceList/{id}")
        public List<String> getRoutePlaces(@PathVariable String id) {
        return routeService.getRoutePlaces(id);
    }
    



}
