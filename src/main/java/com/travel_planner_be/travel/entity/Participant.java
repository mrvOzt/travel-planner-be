package com.travel_planner_be.travel.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class Participant {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    private String surname;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please enter correct format for email")
    private String email;

    @NotBlank(message = "Gender cannot be blank")
    private String gender;
}
