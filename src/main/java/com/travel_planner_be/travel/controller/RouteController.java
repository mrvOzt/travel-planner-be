package com.travel_planner_be.travel.controller;


import com.travel_planner_be.travel.dto.UpdateRouteDTO;
import com.travel_planner_be.travel.entity.Participant;
import com.travel_planner_be.travel.entity.Route;
import com.travel_planner_be.travel.service.RouteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/route")
public class RouteController {


    private final RouteService routeService;

    @PostMapping(value = "/saveRoute")
    public ResponseEntity<Route> saveRoute(@Valid @RequestBody Route route) {
        return routeService.saveRoute(route);
    }

    @GetMapping(value = "/getRoutes/{userId}")
    public List<Route> getUserRoutes(@Valid @PathVariable String userId) {
        return routeService.getRouteByUserId(userId);
    }

    @GetMapping(value = "/getRoutePlaceList/{id}")
        public List<String> getRoutePlaces(@Valid @PathVariable String id) {
        return routeService.getRoutePlaces(id);
    }

    @PostMapping(value = "/cancelRoute")
    public ResponseEntity<String> cancelRoute(@Valid @RequestParam String routeId) {
        return routeService.cancelRoute(routeId);
    }

    @GetMapping(value = "/getParticipants")
    public List<Participant> getParticipants(@Valid @RequestParam String routeId){
        return routeService.getRouteParticipants(routeId);
    }

    @PostMapping(value = "/cancelPlace")
    public ResponseEntity<?> cancelPlace(@Valid @RequestBody UpdateRouteDTO updateRouteDTO){
        return routeService.updateRoutePlace(updateRouteDTO);
    }

    @GetMapping(value = "/getRoutePlaceCoordinates/{id}")
    public List<String> getRoutePlacesCoordinates(@Valid @PathVariable String id) {
        return routeService.getRoutePlaces(id);
    }


}
