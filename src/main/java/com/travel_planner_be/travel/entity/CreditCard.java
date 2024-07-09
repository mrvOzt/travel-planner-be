package com.travel_planner_be.travel.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CreditCard {

    @Id
    private String cardNumber;
    private String userId;
    private String holderName;
    private String holderSurname;
    private String cvv;
    private String expirationDate;
    private double limit;

}
