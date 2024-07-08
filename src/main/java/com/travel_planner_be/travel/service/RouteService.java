package com.travel_planner_be.travel.service;

import com.travel_planner_be.travel.entity.Route;
import com.travel_planner_be.travel.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RouteService {

    @Autowired
    RouteRepository routeRepository;

    public Route saveRoute(Route route) {
        route.setId(UUID.randomUUID().toString());

        return routeRepository.save(route);
    }
    public List<Route> getRouteByUserId(String userId) {
        return routeRepository.findAllByUserId(userId);
    }
    public List<String> getRoutePlaces(String id) {
        return routeRepository.findById(id)
                .map(Route::getPlaces)
                .orElse(null);
    }

    public boolean cancelRoute(String routeId) {
        if (routeRepository.existsById(routeId)) {
            routeRepository.deleteById(routeId);
            return true;
        } else {
            return false;
        }
    }



}
