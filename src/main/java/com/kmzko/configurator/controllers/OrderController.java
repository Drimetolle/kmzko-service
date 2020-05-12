package com.kmzko.configurator.controllers;

import com.kmzko.configurator.dto.RequestToCreateOrderDto;
import com.kmzko.configurator.entity.orders.Order;
import com.kmzko.configurator.mappers.OrderMapper;
import com.kmzko.configurator.services.detailService.OrderDetailsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/user/orders")
public class OrderController {
    private final OrderDetailsService orderDetailsService;
    private final OrderMapper orderMapper;

    public OrderController(OrderDetailsService orderDetailsService, OrderMapper orderMapper) {
        this.orderDetailsService = orderDetailsService;
        this.orderMapper = orderMapper;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getAllOrders(Authentication authentication) {
        return ResponseEntity.ok(orderDetailsService.getAllUserOrders(authentication.getName()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getOrderById(@PathVariable long id, Authentication authentication) {
        return ResponseEntity.ok(orderDetailsService.getUserOrderById(authentication.getName(), id).get());
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> createNewOrder(RequestToCreateOrderDto body, Authentication authentication) {
        Order order = orderDetailsService.save(authentication.getName(), orderMapper.toEntity(body.getOrder()), body.getProjectId());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(order);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUserOrder(@PathVariable long id, Authentication authentication) {
        if (!orderDetailsService.deleteById(authentication.getName(), id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }
}
