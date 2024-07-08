package com.travel_planner_be.travel.controller;

import com.travel_planner_be.travel.dto.FilterDTO;
import com.travel_planner_be.travel.entity.Place;
import com.travel_planner_be.travel.service.RoutePlanningService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@AllArgsConstructor
public class RoutePlanningController {

    @Autowired
    private RoutePlanningService routePlanningService;


    @PostMapping(value = "/getPlacesByCityAndTags")
    public ResponseEntity<Map<String, List<Place>>> getPlacesByCityAndTags(@RequestBody FilterDTO filterDTO) {

        List<Place> filteredPlaces = routePlanningService.getPlacesByTypes(filterDTO.getTags());

        List<Place> placesByCity = filteredPlaces.stream()
                .filter(place -> place.getCity().equalsIgnoreCase(filterDTO.getCity()))
                .collect(Collectors.toList());

        Map<String, List<Place>> groupedPlaces = new HashMap<>();

        for (Place place : placesByCity) {
            String type = place.getType();
            if (!groupedPlaces.containsKey(type)) {
                groupedPlaces.put(type, new ArrayList<>());
            }
            groupedPlaces.get(type).add(place);
        }

        return new ResponseEntity<>(groupedPlaces, HttpStatus.OK);
    }


    @GetMapping("/totalPrice")
    public ResponseEntity<Double> getTotalPriceByPlaceIds(@RequestBody List<String> placeIds) {
        double totalPrice = 0.0;

        for (String placeId : placeIds) {
            Optional<Place> optionalPlace = routePlanningService.getPlaceById(placeId);
            if (optionalPlace.isPresent()) {
                Place place = optionalPlace.get();
                totalPrice += place.getPrice();
            }
        }
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }
}
