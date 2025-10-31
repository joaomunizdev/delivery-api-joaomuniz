package com.deliverytech.delivery.controller;

import com.deliverytech.delivery.entity.Restaurant;
import com.deliverytech.delivery.service.RestaurantService; // Vamos precisar atualizar este serviço
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public ResponseEntity<?> createRestaurant(@RequestBody Restaurant restaurant) {
        try {
            Restaurant novoRestaurante = restaurantService.create(restaurant);
            return new ResponseEntity<>(novoRestaurante, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Ex: "Restaurante já cadastrado com este nome"
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> findRestaurants() {
        return ResponseEntity.ok(restaurantService.findByOrderByRatingDesc());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findRestaurantById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(restaurantService.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/by_category")
    public ResponseEntity<List<Restaurant>> findByCategory(@RequestParam("name") String name) {
        return ResponseEntity.ok(restaurantService.findByCategory(name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deactivateRestaurant(@PathVariable Integer id) {
        try {
            restaurantService.deactivate(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}