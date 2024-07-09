package com.travel_planner_be.travel.service;

import com.travel_planner_be.travel.entity.Participant;
import com.travel_planner_be.travel.entity.Place;
import com.travel_planner_be.travel.entity.Route;
import com.travel_planner_be.travel.entity.User;
import com.travel_planner_be.travel.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;
    private final UserService userService;
    private final PlaceService placeService;

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
        if (routeRepository.existsById(routeId)) {
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
         Route savedRoute = routeRepository.save(route);

         Optional<User> optionalUser = userService.getUserById(route.getUserId());

         for (String placeId : savedRoute.getPlaces()) {
             Optional<Place> selectedPlace = placeService.getPlace(placeId);
             if(selectedPlace.isPresent()){
                 Place place = selectedPlace.get();
                 place.setPopularityRate(place.getPopularityRate() + 1);
                 placeService.updatePlace(place);
             }
             else{
                 return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
             }

         }

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

     public List<Participant> getRouteParticipants(String routeId){
        Optional<Route> route = routeRepository.findById(routeId);
        if(route.isPresent()){
            Route selectedRoute = route.get();
            return selectedRoute.getParticipants();
        }
        return new ArrayList<>();
     }


}
