package com.travel_planner_be.travel.service;

import com.travel_planner_be.travel.dto.PaymentDTO;
import com.travel_planner_be.travel.entity.CreditCard;
import com.travel_planner_be.travel.entity.Route;
import com.travel_planner_be.travel.entity.User;
import com.travel_planner_be.travel.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {


    private final PaymentRepository paymentRepository;
    private final UserService userService;


//    public ResponseEntity<String> approvePayment(PaymentDTO paymentDTO){
//        CreditCard requestCardInfo = paymentDTO.getCreditCard();
//        CreditCard existingCard = paymentRepository.findByCardNumber(requestCardInfo.getCardNumber());
//
//        if (existingCard != null) {
//            Optional<User> optionalUser = userService.getUserById(requestCardInfo.getUserId());
//            if (optionalUser.isPresent()) {
//                User user = optionalUser.get();
//
//
//                if(existingCard.getLimit() >= paymentDTO.getTourPrice()){
//                    return new ResponseEntity<>("Payment successful", HttpStatus.OK);
//                }
//                else{
//                    return new ResponseEntity<>("Card limit is insufficient for transaction",HttpStatus.BAD_REQUEST);
//                }
//            }
//        }
//        return new ResponseEntity<>("Payment failed" , HttpStatus.BAD_REQUEST);
//    }


}
