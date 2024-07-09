package com.travel_planner_be.travel.repository;


import com.travel_planner_be.travel.entity.CreditCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends MongoRepository<CreditCard, String> {

    CreditCard findByCardNumber(String cardNumber);
}
