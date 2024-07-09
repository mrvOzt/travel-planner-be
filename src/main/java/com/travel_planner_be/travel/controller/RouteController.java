package com.travel_planner_be.travel.controller;


import com.travel_planner_be.travel.entity.Place;
import com.travel_planner_be.travel.entity.Route;
import com.travel_planner_be.travel.entity.User;
import com.travel_planner_be.travel.service.PlaceService;
import com.travel_planner_be.travel.service.RouteService;
import com.travel_planner_be.travel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.*;


@RestController
@RequestMapping("api/route")
@RequiredArgsConstructor
public class RouteController {


    private final RouteService routeService;

    @PostMapping(value = "/saveRoute")
    public ResponseEntity<Route> saveRoute(@RequestBody Route route) {
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

    @PostMapping(value = "/cancelRoute")
    public ResponseEntity<String> cancelRoute(@RequestParam String routeId) {
        return routeService.cancelRoute(routeId);
    }




}
