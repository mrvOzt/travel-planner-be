package com.travel_planner_be.travel.service;

import com.travel_planner_be.travel.dto.UpdateRouteDTO;
import com.travel_planner_be.travel.entity.Participant;
import com.travel_planner_be.travel.entity.Place;
import com.travel_planner_be.travel.entity.Route;
import com.travel_planner_be.travel.entity.User;
import com.travel_planner_be.travel.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;
    private final UserService userService;
    private final PlaceService placeService;

    public Optional<Route> getRouteById(String routeId) {
        return routeRepository.findById(routeId);
    }

    public List<Route> getRouteByUserId(String userId) {
        return routeRepository.findAllByUserId(userId);
    }

    public List<String> getRoutePlaces(String id) {
        return routeRepository.findById(id)
                .map(Route::getPlaces)
                .orElse(null);
    }

    public ResponseEntity<String> cancelRoute(String routeId) {
        boolean isDeleted;
        if (routeId == null || routeId.isEmpty()) {
            return new ResponseEntity<>("Invalid routeId", HttpStatus.BAD_REQUEST);
        }
        Optional<Route> route = routeRepository.findById(routeId);
        if (route.isPresent() && route.get().isStatusFlag()) {
            Route existingRoute = route.get();
            Optional<User> user = userService.getUserById(existingRoute.getUserId());
            if (user.isPresent()) {
                User existingUser = user.get();
                List<String> routes = existingUser.getRoutes();
                routes.remove(routeId);
                existingUser.setRoutes(routes);
                userService.saveUser(existingUser);
            }
            routeRepository.deleteById(routeId);
            isDeleted = true;
        } else {
            isDeleted = false;
        }
        if (isDeleted) {
            return new ResponseEntity<>("Tour successfully canceled", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Tour cancellation failed", HttpStatus.NOT_FOUND);
        }
    }

     public ResponseEntity<Route> saveRoute(Route route){
         route.setId(UUID.randomUUID().toString());
         route.setStatusFlag(true);
         long tourDayCount = getTourDayNumber(route.getStartDate(),route.getEndDate());
         if(tourDayCount == -1){
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }
         else{
             for (String placeId : route.getPlaces()) {
                 Optional<Place> selectedPlace = placeService.getPlace(placeId);
                 if(selectedPlace.isPresent()){
                     Place place = selectedPlace.get();
                     place.setPopularityRate(place.getPopularityRate() + 1);
                     getTotalPrice(tourDayCount,place,route);
                     placeService.updatePlace(place);
                 }
                 else{
                     return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                 }

             }
             Optional<User> optionalUser = userService.getUserById(route.getUserId());
             Route savedRoute = routeRepository.save(route);

             if (optionalUser.isPresent()) {
                 User user = optionalUser.get();
                 if (user.getRoutes() == null) {
                     user.setRoutes(new ArrayList<>());
                 }
                 user.getRoutes().add(savedRoute.getId());

                 userService.saveUser(user);

                 return new ResponseEntity<>(savedRoute, HttpStatus.OK);
             } else {

                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
             }
         }

     }

     public ResponseEntity<?> updateRoutePlace(UpdateRouteDTO updateRouteDTO){
         boolean isSuccess;
         if (updateRouteDTO.getRouteId() == null || updateRouteDTO.getRouteId().isEmpty()) {
             return new ResponseEntity<>("Invalid routeId",HttpStatus.BAD_REQUEST);
         }
         Optional<Route> route = routeRepository.findById(updateRouteDTO.getRouteId());
         if (route.isPresent() && route.get().isStatusFlag()) {
             Route existingRoute = route.get();
             List<String> places = existingRoute.getPlaces();
             if(updateRouteDTO.getIsDeleteOperation() == 1){
                 places.remove(updateRouteDTO.getPlaceId());
             }
             else{
                 places.add(updateRouteDTO.getPlaceId());
             }
             existingRoute.setPlaces(places);
             routeRepository.save(existingRoute);
             isSuccess = true;
         } else {
             isSuccess = false;
         }
         if (isSuccess) {
             return new ResponseEntity<>("Route succesfully updated", HttpStatus.OK);
         } else {
             return new ResponseEntity<>("Route update is failed", HttpStatus.NOT_FOUND);
         }
     }

     public List<Participant> getRouteParticipants(String routeId){
        Optional<Route> route = routeRepository.findById(routeId);
        if(route.isPresent()){
            Route selectedRoute = route.get();
            return selectedRoute.getParticipants();
        }
        return new ArrayList<>();
     }

     private long getTourDayNumber(LocalDate startDate, LocalDate endDate){
        if(endDate.isAfter(startDate)){
            return ChronoUnit.DAYS.between(startDate, endDate);
        }
        else{
            return -1;
        }
     }
     private void getTotalPrice(long tourDayCount,Place place,Route route){
        if(Objects.equals(place.getType(), "Hotel")){
            route.setPrice(route.getPrice() + (place.getPrice() * route.getParticipants().size() * tourDayCount));
        }
        else{
            route.setPrice(route.getPrice() + (place.getPrice() * route.getParticipants().size()));
        }
     }


}
