package com.travel_planner_be.travel.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {

    @NotBlank(message = "Email or password is not correct")
    private String email;

    @NotBlank(message = "Email or password is not correct")
    private String password;
}
