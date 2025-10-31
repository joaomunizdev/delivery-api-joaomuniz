package com.deliverytech.delivery.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverytech.delivery.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByEmail(String email);

    List<Client> findByIsActive(boolean isActive);

}
