package com.kmzko.configurator.mappers;

import com.kmzko.configurator.dto.OrderDto;
import com.kmzko.configurator.entity.orders.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper extends AbstractMapper<Order, OrderDto> {
    public OrderMapper() {
        super(Order.class, OrderDto.class);
    }
}
