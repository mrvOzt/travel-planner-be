package com.travel_planner_be.travel.entity;

import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class User {

    @Id
    private String id;

    @NotBlank(message = "Name field cannot be empty")
    @Size(max = 60 , message = "Entered name too long")
    private String name;

    @NotBlank(message = "Surname field cannot be empty")
    @Size(max = 60 , message = "Entered name too long")
    private String surname;

    @NotBlank(message = "Email field cannot be empty")
    @Email(message = "Please enter correct email format")
    private String email;

    @NotBlank(message = "Password field cannot be empty")
    @Size(max = 8,message = "Please enter max 8 character for password")
    private String password;

    @NotBlank(message = "Phone number field cannot be empty")
    @Size(max = 10 , message = "Enter the phone number in 10 digits")
    private String phoneNumber;

    @NotBlank(message = "Routes field cannot be empty")
    private List<String> routes;

    @Nullable
    private List<String> paymentMethods;

    @NotBlank(message = "Please fill role field")
    private String role;

}