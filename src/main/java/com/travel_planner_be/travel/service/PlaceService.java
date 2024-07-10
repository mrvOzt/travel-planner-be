package com.travel_planner_be.travel.service;


import com.travel_planner_be.travel.dto.FilterDTO;
import com.travel_planner_be.travel.entity.Place;
import com.travel_planner_be.travel.entity.Route;
import com.travel_planner_be.travel.entity.User;
import com.travel_planner_be.travel.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PlaceService {


    private final PlaceRepository placeRepository;

    public Place savePlace(Place place) {
        place.setId(UUID.randomUUID().toString());
        return placeRepository.save(place);
    }
    public Optional<Place> getPlace(String id) {
        return placeRepository.findById(id);
    }

    public void updatePlace(Place place) {
        placeRepository.save(place);
    }
    public List<Place> findByCityAndTypeOrderByPopularityRateDesc(String city, String type, Pageable pageable){
        return placeRepository.findByCityAndTypeOrderByPopularityRateDesc(city,type,pageable);
    }

    public List<Place> findByCityOrderByPopularityRateDesc(String city, Pageable pageable){
        return placeRepository.findByCityOrderByPopularityRateDesc(city,pageable);
    }

    public List<Place> getPlaceByCity(String city) {

        return placeRepository.findAllByCity(city);
    }

    public List<Place> getPopularPlaces(int top, String type,String city){
        Pageable pageable = PageRequest.of(0, top, Sort.by(Sort.Direction.DESC, "popularityRate"));
        List<Place> popularPlaces;

        if (type != null && !type.isEmpty()) {
            popularPlaces = placeRepository.findByCityAndTypeOrderByPopularityRateDesc(city, type, pageable);
        } else {
            popularPlaces = placeRepository.findByCityOrderByPopularityRateDesc(city, pageable);
        }
        return popularPlaces;
    }

    public ResponseEntity<Map<String, List<Place>>> getFilteredPlaces(FilterDTO filterDTO){
        List<Place> filteredPlaces;
        if(filterDTO.getTags().isEmpty() ){
            filteredPlaces = placeRepository.findAll();
        }
        else{
            filteredPlaces = placeRepository.findByTagIn(filterDTO.getTags());
        }
        List<Place> placesByCity = filteredPlaces.stream()
                .filter(place -> place.getCity().equalsIgnoreCase(filterDTO.getDestination()))
                .toList();

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

    public ResponseEntity<Double> getTotalPrice(List<String> placeIds){
        double totalPrice = 0.0;

        for (String placeId : placeIds) {
            Optional<Place> optionalPlace = placeRepository.findById(placeId);
            if (optionalPlace.isPresent()) {
                Place place = optionalPlace.get();
                totalPrice += place.getPrice();
            }
        }
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }




}
