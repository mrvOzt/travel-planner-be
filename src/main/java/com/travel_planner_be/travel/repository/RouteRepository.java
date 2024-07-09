package com.travel_planner_be.travel.repository;

import com.travel_planner_be.travel.entity.Participant;
import com.travel_planner_be.travel.entity.Route;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RouteRepository extends MongoRepository<Route, String> {

    List<Route> findAllByUserId(String userID);

}
