package com.travel_planner_be.travel.service;

import com.travel_planner_be.travel.dto.LoginDTO;
import com.travel_planner_be.travel.entity.User;
import com.travel_planner_be.travel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    public ResponseEntity<?> saveUser(User user) {
        if (user.getId() == null) {
            user.setId(UUID.randomUUID().toString());
            Optional<User> savedUser = userRepository.findByEmail(user.getEmail());
            if(savedUser.isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already registered");
            }
        } else {
            Optional<User> existingUser = userRepository.findById(user.getId());
            if (existingUser.isPresent()) {
                User updatedUser = existingUser.get();
                updatedUser.setName(user.getName());
                updatedUser.setSurname(user.getSurname());
                updatedUser.setEmail(user.getEmail());
                updatedUser.setPassword(user.getPassword());
                updatedUser.setPhoneNumber(user.getPhoneNumber());
                updatedUser.setRoutes(user.getRoutes());
                updatedUser.setPaymentMethods(user.getPaymentMethods());
                updatedUser.setRole(user.getRole());
                return ResponseEntity.ok(userRepository.save(updatedUser));
            } else {

                ResponseEntity.notFound();
            }
        }
        return ResponseEntity.ok(userRepository.save(user));
    }


    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO) {
        Optional<User> user = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    public Optional<User> getUserById(String id){

        return userRepository.findById(id);
    }






}
