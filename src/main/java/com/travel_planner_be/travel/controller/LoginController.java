package com.travel_planner_be.travel.controller;


import com.travel_planner_be.travel.dto.UserDTO;
import com.travel_planner_be.travel.entity.User;
import com.travel_planner_be.travel.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("api/login/")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String login(String email, String password) {
        UserDTO userDTO=new UserDTO(email,password);
        return loginService.login(userDTO);
    }
}
