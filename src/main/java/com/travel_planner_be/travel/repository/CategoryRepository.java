package com.travel_planner_be.travel.repository;

import java.util.Optional;
import com.travel_planner_be.travel.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

    Optional<Category> findByName(String name);

}
