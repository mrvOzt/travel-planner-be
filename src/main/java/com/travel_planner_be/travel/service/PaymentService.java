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

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {


    private final PaymentRepository paymentRepository;
    private final UserService userService;

    public CreditCard getByCardNumber(String cardNumber) {
        return paymentRepository.findByCardNumber(cardNumber);
    }

    public CreditCard savePaymentMethod(CreditCard creditCard) {

        return paymentRepository.save(creditCard);
    }

    public ResponseEntity<String> approvePayment(CreditCard creditCard){
        CreditCard existingCard = getByCardNumber(creditCard.getCardNumber());

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

}
