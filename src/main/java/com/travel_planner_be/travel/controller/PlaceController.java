package com.travel_planner_be.travel.controller;


import com.travel_planner_be.travel.entity.Place;
import com.travel_planner_be.travel.entity.Route;
import com.travel_planner_be.travel.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/place")
public class PlaceController {

    @Autowired
    private PlaceService placeService;


    @PostMapping(value ="/savePlace")
    public Place savePlace(@RequestBody Place place) {
        return placeService.savePlace(place);
    }


    @GetMapping(value = "/getPlace/{id}")
    public Place getPlace(@PathVariable String id) {
        return placeService.getPlace(id);
    }

    @DeleteMapping("/deletePlace/{id}")
    public ResponseEntity<?> deletePlace(@PathVariable String id) {
        return  placeService.deletePlace(id);
    }

    @GetMapping("/getAllPlaces")
    public List<Place> getAllPlaces() {
        return placeService.getAllPlaces();
    }


}
