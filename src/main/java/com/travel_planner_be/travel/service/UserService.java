package com.travel_planner_be.travel.service;

import com.travel_planner_be.travel.dto.LoginDTO;
import com.travel_planner_be.travel.entity.User;
import com.travel_planner_be.travel.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;


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

    private Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    private static String alphaNumericString(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }



    public ResponseEntity<?> forgotPassword(String email) throws MessagingException {
        Optional<User> tempUser = userRepository.findByEmail(email);
        if (tempUser.isPresent()) {
            String pass=sendEmailForgottenPassword(tempUser.get());
            updatePassword(tempUser.get().getId(), pass);
            return ResponseEntity.ok("We send your new password to your email.");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    private String sendEmailForgottenPassword(User tempUser) throws MessagingException {

        String freshPassword = alphaNumericString(8);
        Context context = new Context();
        context.setVariable("email", tempUser.getEmail());
        context.setVariable("newPassword", freshPassword);
        String htmlBody = templateEngine.process("password", context);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(tempUser.getEmail());
        helper.setSubject("Here is your new password");
        helper.setText(htmlBody, true);
        javaMailSender.send(message);
        return freshPassword;
    }

    private void updatePassword(String id, String newPassword) {
        User user = userRepository.findById(id).get();
        user.setPassword(newPassword);
        userRepository.save(user);
    }
}
