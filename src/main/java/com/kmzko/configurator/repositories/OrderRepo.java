package com.kmzko.configurator.repositories;

import com.kmzko.configurator.entity.orders.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    Optional<Order> findOrdersByConveyorProjectId(long id, long project_id);
}
