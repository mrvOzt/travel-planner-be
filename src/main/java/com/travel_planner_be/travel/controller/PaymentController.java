package com.travel_planner_be.travel.controller;

import com.travel_planner_be.travel.entity.Route;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/payment/")
public class PaymentController {

    @PostMapping(value ="/approvePayment")
    public ResponseEntity<String> approvePayment() {
        return new ResponseEntity<>("Ödeme başarılı", HttpStatus.OK);
    }
}
