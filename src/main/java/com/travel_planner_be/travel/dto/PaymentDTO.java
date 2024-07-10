package com.travel_planner_be.travel.dto;

import com.travel_planner_be.travel.entity.CreditCard;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO {

    @NotBlank(message = "Please enter all credit card information")
    private CreditCard creditCard;

    @NotBlank(message = "Please enter routeId")
    private String routeId;
}
