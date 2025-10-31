package com.deliverytech.delivery.controller;

import com.deliverytech.delivery.entity.Order;
import com.deliverytech.delivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        try {
            Order novoPedido = orderService.create(order);
            return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/by_client")
    public ResponseEntity<List<Order>> findByClient(@RequestParam("id") Integer clienteId) {
        return ResponseEntity.ok(orderService.findByClient(clienteId));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Integer id, @RequestBody Map<String, String> body) {
        try {
            String newStatus = body.get("newStatus");
            if (newStatus == null || newStatus.isEmpty()) {
                return ResponseEntity.badRequest().body("Campo 'newStatus' é obrigatório.");
            }

            Order pedido = orderService.changeStatus(id, newStatus);
            return ResponseEntity.ok(pedido);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}