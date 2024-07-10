package com.travel_planner_be.travel.service;

import com.travel_planner_be.travel.dto.PaymentDTO;
import com.travel_planner_be.travel.entity.CreditCard;
import com.travel_planner_be.travel.entity.Route;
import com.travel_planner_be.travel.entity.User;
import com.travel_planner_be.travel.repository.PaymentRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {


    private final PaymentRepository paymentRepository;
    private final UserService userService;
    private final RouteService routeService;
    private final EmailService emailService;


    public ResponseEntity<?> approvePayment(PaymentDTO paymentDTO) throws MessagingException {
        CreditCard requestCardInfo = paymentDTO.getCreditCard();
        CreditCard existingCard = paymentRepository.findByCardNumber(requestCardInfo.getCardNumber());

        if (existingCard != null) {
            Optional<User> optionalUser = userService.getUserById(requestCardInfo.getUserId());
            if (optionalUser.isPresent()) {
                Optional<Route> route = routeService.getRouteById(paymentDTO.getRouteId());
                if(route.isPresent()){
                    Route existingRoute = route.get();
                    if(existingCard.getLimit() >= existingRoute.getPrice()){
                        emailService.sendHtmlEmail(optionalUser.get().getEmail(), "Your Travel Invoice",existingRoute.getId());
                        return new ResponseEntity<>(existingRoute, HttpStatus.OK);
                    }
                    else{
                        return new ResponseEntity<>("Card limit is insufficient for transaction",HttpStatus.BAD_REQUEST);
                    }
                }
            }
        }
        return new ResponseEntity<>("Payment failed" , HttpStatus.BAD_REQUEST);
    }
}
