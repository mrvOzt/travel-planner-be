package com.travel_planner_be.travel.repository;



import com.travel_planner_be.travel.entity.Place;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlaceRepository extends MongoRepository<Place, String> {

}
