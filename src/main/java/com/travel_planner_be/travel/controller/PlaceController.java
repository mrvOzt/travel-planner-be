package com.travel_planner_be.travel.controller;


import com.travel_planner_be.travel.dto.FilterDTO;
import com.travel_planner_be.travel.entity.Place;
import com.travel_planner_be.travel.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/place")
@RequiredArgsConstructor
public class PlaceController {


    private PlaceService placeService;

    @PostMapping(value = "/savePlace")
    public Place savePlace(@RequestBody Place place) {
        return placeService.savePlace(place);
    }

    @GetMapping(value = "/getPlace/{id}")
    public Optional<Place> getPlace(@PathVariable String id) {
        return placeService.getPlace(id);
    }

    @PostMapping(value = "/filterPlace")
    public ResponseEntity<Map<String, List<Place>>> getPlacesByCityAndTags(@RequestBody FilterDTO filterDTO) {
        return placeService.getFilteredPlaces(filterDTO);
    }

    @GetMapping("/totalPrice")
    public ResponseEntity<Double> getTotalPriceByPlaceIds(@RequestBody List<String> placeIds) {
        return placeService.getTotalPrice(placeIds);
    }

    @PostMapping(value = "/getPopularPlaces")
    public List<Place> getPopularPlaces(@RequestParam(defaultValue = "4", required = false) int top,
                                        @RequestParam(required = false) String type,
                                        @RequestParam String city) {

        return placeService.getPopularPlaces(top,type,city);
    }

}
