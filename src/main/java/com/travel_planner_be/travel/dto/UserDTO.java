package com.travel_planner_be.travel.dto;

import com.travel_planner_be.travel.entity.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {
    private String id;
    private String name;
    private String surname;
    private String username;
    private String phone_number;


    public static UserDTO from(User user) {
        return builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .username(user.getUsername())
                .phone_number(user.getPhone_number())
                .build();
    }
}
