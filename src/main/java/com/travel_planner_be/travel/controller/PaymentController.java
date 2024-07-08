package com.travel_planner_be.travel.controller;

import com.travel_planner_be.travel.entity.CreditCard;
import com.travel_planner_be.travel.entity.Route;
import com.travel_planner_be.travel.entity.User;
import com.travel_planner_be.travel.service.PaymentService;
import com.travel_planner_be.travel.service.UserService;
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
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private UserService userService;

    @PostMapping(value ="/approvePayment")
    public ResponseEntity<String> approvePayment(@RequestBody CreditCard creditCard) {


        return new ResponseEntity<>("Ödeme başarılı", HttpStatus.OK);
    }

    @PostMapping(value = "/savePaymentMethod")
    public ResponseEntity<?> savePaymentMethod(@RequestBody CreditCard creditCard) {
        CreditCard existingCard = paymentService.getByCardNumber(creditCard.getCardNumber());
        if (existingCard != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Bu kredi kartı numarası zaten kayıtlıdır.");
        }
        System.out.println(creditCard.getUserId());

        Optional<User> optionalUser = userService.getUserById(creditCard.getUserId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (!user.getName().equals(creditCard.getHolderName()) || !user.getSurname().equals(creditCard.getHolderSurname())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Kredi kartı bilgileri kullanıcı adı ve soyadı ile eşleşmiyor.");
            }

            if (user.getPaymentMethods() == null) {
                user.setPaymentMethods(new ArrayList<>());
            }
            CreditCard savedCard = paymentService.savePaymentMethod(creditCard);
            user.getPaymentMethods().add(savedCard.getCardNumber());
            userService.saveUser(user);

            return ResponseEntity.ok(savedCard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
