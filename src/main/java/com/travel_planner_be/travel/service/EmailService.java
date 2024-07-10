package com.travel_planner_be.travel.service;

import com.travel_planner_be.travel.entity.Place;
import com.travel_planner_be.travel.entity.Route;
import com.travel_planner_be.travel.entity.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final RouteService routeService;
    private final UserService userService;
    private final PlaceService placeService;

    public void sendHtmlEmail(String to, String subject, String routeId) throws MessagingException {

        Map<String, Object> variables = new HashMap<>();

        Optional<Route> route = routeService.getRouteById(routeId);

        if(route.isPresent()){
            Route existingRoute = route.get();
            Optional<User> user = userService.getUserById(existingRoute.getUserId());

            if(user.isPresent()){
                User existingUser = user.get();
                variables.put("date", LocalDate.now());
                variables.put("invoiceNumber", existingRoute.getId());
                variables.put("companyName", "Travel Planner");
                variables.put("companyAddress", "Doğuş Caddesi No: 207/Z DEÜ Tınaztepe Yerleşkesi Buca 35390 İzmir Türkiye");
                variables.put("customerName", existingUser.getName() + " " + existingUser.getSurname());
                variables.put("customerEmail", existingUser.getEmail());
                variables.put("userLocation", existingRoute.getDeparture());
                variables.put("routeLocation", existingRoute.getDestination());
                variables.put("duration", existingRoute.getStartDate() + " / " + existingRoute.getEndDate());
                List<String> placeIds = existingRoute.getPlaces();
                List<Place> routePlaces = new ArrayList<>();
                for(String id : placeIds){
                    Optional<Place> place = placeService.getPlace(id);
                    place.ifPresent(routePlaces::add);
                }
                variables.put("places",routePlaces);
                variables.put("participants",existingRoute.getParticipants());
                variables.put("price", existingRoute.getPrice());
            }
        }

        Context context = new Context();
        context.setVariables(variables);

        String htmlBody = templateEngine.process("invoice", context);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        javaMailSender.send(message);
    }
}
