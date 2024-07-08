package com.travel_planner_be.travel.service;


import com.travel_planner_be.travel.dto.UserDTO;
import com.travel_planner_be.travel.entity.User;
import com.travel_planner_be.travel.repository.LoginRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {

    private LoginRepository loginRepository;

    public String login(UserDTO userDTO) {
        Optional<User> userOptional = loginRepository.findByEmail(userDTO.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getId();
        }

        return "Not found";
    }
}
