package com.travel_planner_be.travel.repository;

import com.travel_planner_be.travel.entity.Place;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends MongoRepository<Place, String> {

    List<Place> findAllByGenderPreference(String genderPreference);

    List<Place> findAllByType(String type);

}
