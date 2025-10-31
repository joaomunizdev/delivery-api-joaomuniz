package com.deliverytech.delivery.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deliverytech.delivery.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

        List<Order> findByClientId(int clientId);

        List<Order> findByStatus(String status);

        @Query("SELECT p FROM Order p " +
                        "WHERE p.status = :status " +
                        "AND p.orderDate BETWEEN :startDate AND :endDate")
        List<Order> findByStatusAndDateInterval(
                        @Param("status") String status,
                        @Param("startDate") Date startDate,
                        @Param("endDate") Date endDate);
}
