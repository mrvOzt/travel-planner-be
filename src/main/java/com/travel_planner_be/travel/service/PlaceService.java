package com.travel_planner_be.travel.service;


import com.travel_planner_be.travel.entity.Place;
import com.travel_planner_be.travel.entity.Route;
import com.travel_planner_be.travel.repository.PlaceRepository;
import com.travel_planner_be.travel.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlaceService {

    @Autowired
    PlaceRepository placeRepository;

    public Place savePlace(Place place) {
        place.setId(UUID.randomUUID().toString());
        return placeRepository.save(place);
    }

    public Place getPlace(String id) {
        return placeRepository.findById(id).orElse(null);
    }

}
