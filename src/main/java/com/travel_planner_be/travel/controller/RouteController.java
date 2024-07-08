package com.travel_planner_be.travel.controller;


import com.travel_planner_be.travel.entity.Route;
import com.travel_planner_be.travel.entity.User;
import com.travel_planner_be.travel.service.RouteService;

import com.travel_planner_be.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/route/")
public class RouteController {

    @Autowired
    private RouteService routeService;
    @Autowired
    private UserService userService;



    @PostMapping(value = "/saveRoute")
    public ResponseEntity<Route> saveRoute(@RequestBody Route route) {

        Route savedRoute = routeService.saveRoute(route);

        Optional<User> optionalUser = userService.getUserById(route.getUserId());

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
            return new ResponseEntity<>("Geçersiz routeId", HttpStatus.BAD_REQUEST);
        }

        boolean isDeleted = routeService.cancelRoute(routeId);
        if (isDeleted) {
            return new ResponseEntity<>("Tur başarıyla iptal edildi", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Tur iptali başarısız oldu", HttpStatus.NOT_FOUND);
        }
    }




}
