package com.travel_planner_be.travel.controller;


import com.travel_planner_be.travel.dto.LoginDTO;
import com.travel_planner_be.travel.entity.User;
import com.travel_planner_be.travel.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;


@RequestMapping("api/user")
@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) throws MessagingException {
        return userService.forgotPassword(email);
    }
}
