package com.travel_planner_be.travel.service;

import com.travel_planner_be.travel.entity.Place;
import com.travel_planner_be.travel.repository.RoutePlanningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RoutePlanningService {


    private final RoutePlanningRepository routePlanningRepository;


    public List<Place> createRoutePlanning(List<String> types) {
        return routePlanningRepository.findByTypeIn(types);
    }
}
