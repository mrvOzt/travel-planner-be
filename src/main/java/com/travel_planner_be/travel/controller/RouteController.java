package com.travel_planner_be.travel.controller;


import com.travel_planner_be.travel.dto.SignupDTO;
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

    @PostConstruct
    public void init(){
        Route route = new Route();
        route.setUserId("6686a5d88164ad75da4e74f0");
        route.setPrice(1000);
        route.setStartDate(LocalDate.of(2024, 7, 1));
        route.setEndDate(LocalDate.of(2024,7,25));
        route.setStatusFlag(1);


    }

    @PostMapping(value ="/saveRoute")
    public Route saveRoute(@RequestBody Route route) {
        return routeService.saveRoute(route);
    }

    @GetMapping(value = "/getRoutes/{userId}")
    public List<Route> getUserRoutes(@RequestBody String userId) {
        return routeService.getRouteByUserId(userId);
    }

}
