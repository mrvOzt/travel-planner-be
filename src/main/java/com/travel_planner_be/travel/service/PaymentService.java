package com.travel_planner_be.travel.service;

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


    public ResponseEntity<String> approvePayment(CreditCard creditCard){
        CreditCard existingCard = paymentRepository.findByCardNumber(creditCard.getCardNumber());

        if (existingCard != null) {
            Optional<User> optionalUser = userService.getUserById(creditCard.getUserId());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

                if (!user.getName().equals(creditCard.getHolderName()) || !user.getSurname().equals(creditCard.getHolderSurname())) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("Kredi kartı bilgileri eşleşmiyor.");
                }
            }
        }

        return new ResponseEntity<>("Ödeme başarılı", HttpStatus.OK);
    }

    public ResponseEntity<?> savePaymentMethod(CreditCard creditCard){
        CreditCard existingCard = paymentRepository.findByCardNumber(creditCard.getCardNumber());
        if (existingCard != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("This credit card number is already registered.");
        }

        Optional<User> optionalUser = userService.getUserById(creditCard.getUserId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.getPaymentMethods() == null) {
                user.setPaymentMethods(new ArrayList<>());
            }
            CreditCard savedCard = paymentRepository.save(creditCard);
            user.getPaymentMethods().add(savedCard.getCardNumber());
            userService.saveUser(user);

            return ResponseEntity.ok(savedCard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
