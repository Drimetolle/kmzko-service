package com.kmzko.configurator.repositories;

import com.kmzko.configurator.entity.orders.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
