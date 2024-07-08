package com.travel_planner_be.travel.service;

import com.travel_planner_be.travel.entity.CreditCard;
import com.travel_planner_be.travel.entity.Route;
import com.travel_planner_be.travel.repository.PaymentRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public CreditCard getByCardNumber(String cardNumber) {
        return paymentRepository.findByCardNumber(cardNumber);
    }

    public CreditCard savePaymentMethod(CreditCard creditCard) {

        return paymentRepository.save(creditCard);
    }

}
