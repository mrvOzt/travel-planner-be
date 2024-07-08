package com.travel_planner_be.travel.repository;

import com.travel_planner_be.travel.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LoginRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

}
