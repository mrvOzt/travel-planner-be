package com.travel_planner_be.travel.entity;

import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CreditCard {

    @Id
    @NotBlank(message = "Please check credit card information")
    @Size(max = 16 , min = 16,message = "Card number must be at least 16 characters long")
    private String cardNumber;

    @NotBlank(message = "Please enter a valid userId")
    private String userId;

    @NotBlank(message = "Please check credit card information")
    private String holderName;

    @NotBlank(message = "Please check credit card information")
    private String holderSurname;

    @NotBlank(message = "Please check credit card information")
    @Size(max = 3,min = 3,message = "Enter the phone number in 10 digits")
    private String cvv;


    @NotBlank(message = "Please check credit card information")
    private String expirationDate;

    @Nullable
    private double limit;

}
