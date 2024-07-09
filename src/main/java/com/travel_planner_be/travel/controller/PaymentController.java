package com.travel_planner_be.travel.controller;

import com.travel_planner_be.travel.entity.CreditCard;
import com.travel_planner_be.travel.entity.Route;
import com.travel_planner_be.travel.entity.User;
import com.travel_planner_be.travel.service.PaymentService;
import com.travel_planner_be.travel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private PaymentService paymentService;
    private UserService userService;

    @PostMapping(value ="/approvePayment")
    public ResponseEntity<String> approvePayment(@RequestBody CreditCard creditCard) {
        return paymentService.approvePayment(creditCard);
    }

    @PostMapping(value = "/savePaymentMethod")
    public ResponseEntity<?> savePaymentMethod(@RequestBody CreditCard creditCard) {
        return paymentService.savePaymentMethod(creditCard);
    }

}
