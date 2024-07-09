package com.travel_planner_be.travel.controller;


import com.travel_planner_be.travel.dto.FilterDTO;
import com.travel_planner_be.travel.entity.Place;
import com.travel_planner_be.travel.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("api/place")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @PostMapping(value = "/savePlace")
    public Place savePlace(@RequestBody Place place) {
        return placeService.savePlace(place);
    }

    @GetMapping(value = "/getPlace/{id}")
    public Place getPlace(@PathVariable String id) {
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
