package com.deliverytech.delivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverytech.delivery.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByRestaurantId(int restaurantId);

    List<Product> findByCategory(String category);

    List<Product> findByIsAvailable(boolean isAvailable);

}
