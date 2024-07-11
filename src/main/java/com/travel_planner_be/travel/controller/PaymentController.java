package com.travel_planner_be.travel.controller;

import com.travel_planner_be.travel.dto.PaymentDTO;
import com.travel_planner_be.travel.entity.CreditCard;
import com.travel_planner_be.travel.service.PaymentService;
import com.travel_planner_be.travel.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping(value ="/approvePayment")
    public ResponseEntity<?> approvePayment(@RequestBody PaymentDTO paymentDTO) throws MessagingException {
        return paymentService.approvePayment(paymentDTO);
    }
}
