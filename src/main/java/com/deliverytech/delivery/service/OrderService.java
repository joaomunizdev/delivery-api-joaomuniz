package com.deliverytech.delivery.service;

import com.deliverytech.delivery.entity.Client;
import com.deliverytech.delivery.entity.Order;
import com.deliverytech.delivery.repository.ClientRepository;
import com.deliverytech.delivery.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class OrderService {

    private static final String PENDING_STATUS = "PENDENTE";

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ClientRepository clientRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
    }

    public Order create(Order order) {
        Client client = clientRepository.findById(order.getClientId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        if (!client.getIsActive()) {
            throw new RuntimeException("Cliente inativo não pode criar pedidos.");
        }

        order.setStatus(PENDING_STATUS);
        order.setOrderDate(new Date(System.currentTimeMillis()));

        return orderRepository.save(order);
    }

    public Order findById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com ID: " + id));
    }

    public List<Order> findByClient(Integer clientId) {
        return orderRepository.findByClientId(clientId);
    }

    public Order changeStatus(Integer id, String newStatus) {

        Order order = findById(id);
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }
}