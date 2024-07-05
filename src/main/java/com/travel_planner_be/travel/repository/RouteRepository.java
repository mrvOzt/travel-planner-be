package com.travel_planner_be.travel.repository;

import com.travel_planner_be.travel.entity.Place;
import com.travel_planner_be.travel.entity.Route;
import com.travel_planner_be.travel.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RouteRepository extends MongoRepository<Route, String> {

    Optional<List<Route>> findByUserId(String userID);
}
