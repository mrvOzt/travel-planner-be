package com.travel_planner_be.travel.controller;


import com.travel_planner_be.travel.entity.Place;
import com.travel_planner_be.travel.entity.Route;
import com.travel_planner_be.travel.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/place/")
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


}
