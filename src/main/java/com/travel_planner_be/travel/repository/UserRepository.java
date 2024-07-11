package com.travel_planner_be.travel.repository;

import com.travel_planner_be.travel.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmailAndPassword(String email,String password);

    Optional<User> findByEmail(String email);

}