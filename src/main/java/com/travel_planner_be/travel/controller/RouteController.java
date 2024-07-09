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
    private final UserService userService;
    private final PlaceService placeService;


    @PostMapping(value = "/saveRoute")
    public ResponseEntity<Route> saveRoute(@RequestBody Route route) {

        Route savedRoute = routeService.saveRoute(route);

        Optional<User> optionalUser = userService.getUserById(route.getUserId());

        for (String placeId : savedRoute.getPlaces()) {
            Place selectedPlace = placeService.getPlace(placeId);
            selectedPlace.setPopularityRate(selectedPlace.getPopularityRate() + 1);
            placeService.updatePlace(selectedPlace);
        }

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getRoutes() == null) {
                user.setRoutes(new ArrayList<>());
            }
            user.getRoutes().add(savedRoute.getId());

            userService.saveUser(user);

            return new ResponseEntity<>(savedRoute, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/getPopularPlaces")
    public List<Place> getPopularPlaces(@RequestParam(defaultValue = "4", required = false) int top,
                                        @RequestParam(required = false) String type,
                                        @RequestParam String city) {

        Pageable pageable = PageRequest.of(0, top, Sort.by(Sort.Direction.DESC, "popularityRate"));
        List<Place> popularPlaces;


        if (type != null && !type.isEmpty()) {
            popularPlaces = placeService.findByCityAndTypeOrderByPopularityRateDesc(city, type, pageable);
        } else {
            popularPlaces = placeService.findByCityOrderByPopularityRateDesc(city, pageable);
        }
        return popularPlaces;
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
        if (routeId == null || routeId.isEmpty()) {
            return new ResponseEntity<>("Invalid routeId", HttpStatus.BAD_REQUEST);
        }

        boolean isDeleted = routeService.cancelRoute(routeId);
        if (isDeleted) {
            return new ResponseEntity<>("Tour successfully canceled", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Tour cancellation failed", HttpStatus.NOT_FOUND);
        }
    }




}
