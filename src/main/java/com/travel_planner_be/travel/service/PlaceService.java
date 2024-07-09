package com.travel_planner_be.travel.service;


import com.travel_planner_be.travel.entity.Place;
import com.travel_planner_be.travel.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlaceService {

    @Autowired
    private final PlaceRepository placeRepository;

    public Place savePlace(Place place) {
        place.setId(UUID.randomUUID().toString());
        return placeRepository.save(place);
    }
    public Place getPlace(String id) {
        return placeRepository.findById(id).orElse(null);
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


}
