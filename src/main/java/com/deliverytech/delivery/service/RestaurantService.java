package com.deliverytech.delivery.service;

import com.deliverytech.delivery.entity.Restaurant;
import com.deliverytech.delivery.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant create(Restaurant restaurant) {
        if (!restaurantRepository.findByName(restaurant.getName()).isEmpty()) {
            throw new RuntimeException("Restaurante já cadastrado com este nome.");
        }

        restaurant.setIsActive(true);
        restaurant.setRating(0.0f);

        return restaurantRepository.save(restaurant);
    }

    public Restaurant findById(Integer id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado com ID: " + id));
    }

    public List<Restaurant> findByCategory(String name) {
        return restaurantRepository.findByCategory(name);
    }

    public List<Restaurant> findByOrderByRatingDesc() {
        return restaurantRepository.findByOrderByRating();
    }

    public Restaurant activate(Integer id) {
        Restaurant restaurant = findById(id);
        restaurant.setIsActive(true);
        return restaurantRepository.save(restaurant);
    }

    public Restaurant deactivate(Integer id) {
        Restaurant restaurant = findById(id);
        restaurant.setIsActive(false);
        return restaurantRepository.save(restaurant);
    }
}