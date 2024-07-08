package com.travel_planner_be.travel.service;

import com.travel_planner_be.travel.entity.Place;
import com.travel_planner_be.travel.repository.RoutePlanningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoutePlanningService {

    private final RoutePlanningRepository routePlanningRepository;


    public List<Place> getPlacesByTypes(List<String> types) {

        return routePlanningRepository.findByTagIn(types);
    }

    public List<Place> getPlaceByCity(String city) {

        return routePlanningRepository.findAllByCity(city);
    }

    public Optional<Place> getPlaceById(String id) {

        return routePlanningRepository.findById(id);
    }


}
