package com.travel_planner_be.travel.repository;



import com.travel_planner_be.travel.entity.Place;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;


@Repository
public interface PlaceRepository extends MongoRepository<Place, String> {
    List<Place> findByCityAndTypeOrderByPopularityRateDesc(String city, String type, Pageable pageable);

    List<Place> findByCityOrderByPopularityRateDesc(String city, Pageable pageable);

    List<Place> findByTagIn(List<String> type);

    List<Place> findAllByCity(String city);
}
