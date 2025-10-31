package com.deliverytech.delivery.service;

import com.deliverytech.delivery.entity.Product;
import com.deliverytech.delivery.entity.Restaurant;
import com.deliverytech.delivery.repository.ProductRepository;
import com.deliverytech.delivery.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, RestaurantRepository restaurantRepository) {
        this.productRepository = productRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Product create(Product product) {
        Restaurant restaurant = restaurantRepository.findById(product.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado."));

        if (!restaurant.getIsActive()) {
            throw new RuntimeException("Não é possível cadastrar produtos para um restaurante inativo.");
        }

        product.setIsAvailable(true);

        return productRepository.save(product);
    }

    public Product findById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
    }

    public List<Product> findByRestaurantId(Integer restaurantId) {
        return productRepository.findByRestaurantId(restaurantId);
    }

    public Product changeAvailability(Integer id, boolean available) {
        Product product = findById(id);
        product.setIsAvailable(available);
        return productRepository.save(product);
    }

    public void delete(Integer id) {
        Product product = findById(id);
        productRepository.delete(product);
    }
}