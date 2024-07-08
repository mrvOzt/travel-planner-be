package com.travel_planner_be.travel.dto;

import lombok.Data;

@Data
public class UserDTO {
    public UserDTO(String email, String password) {

    }
    private String email;
    private String password;
}
