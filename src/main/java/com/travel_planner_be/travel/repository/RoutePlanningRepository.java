package com.travel_planner_be.travel.repository;

import com.travel_planner_be.travel.entity.Place;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutePlanningRepository extends MongoRepository <Place,String>{

    List<Place> findByTagIn(List<String> type);
    List<Place> findAllByCity(String city);

}
