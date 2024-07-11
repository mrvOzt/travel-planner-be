package com.travel_planner_be.travel.dto;

import com.travel_planner_be.travel.entity.CreditCard;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO {

    private CreditCard creditCard;

    private String routeId;
}
