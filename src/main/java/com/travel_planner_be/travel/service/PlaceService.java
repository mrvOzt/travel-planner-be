package com.travel_planner_be.travel.service;


import com.travel_planner_be.travel.entity.Place;
import com.travel_planner_be.travel.entity.Route;
import com.travel_planner_be.travel.repository.PlaceRepository;
import com.travel_planner_be.travel.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    public ResponseEntity<?> deletePlace(String id) {
        if (placeRepository.existsById(id)) {
            placeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public List<Place> getAllPlacesByPref(String pref) {
        switch (pref) {
            case "m", "w":{
                return placeRepository.findAllByGenderPreference(pref);
            }
            case "h":{
                return placeRepository.findAllByType("Hotel");
            }
        }

        return Collections.emptyList();
    }
}
