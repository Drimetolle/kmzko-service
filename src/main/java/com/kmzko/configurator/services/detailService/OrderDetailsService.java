package com.kmzko.configurator.services.detailService;

import com.kmzko.configurator.entity.orders.Order;
import com.kmzko.configurator.entity.orders.OrderStatus;
import com.kmzko.configurator.entity.user.ConveyorProject;
import com.kmzko.configurator.repositories.ConveyorProjectRepo;
import com.kmzko.configurator.repositories.OrderRepo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailsService {
    private final OrderRepo orderRepo;
    private final UserService userService;
    private final ConveyorProjectRepo conveyorProjectRepo;

    public OrderDetailsService(OrderRepo orderRepo, UserService userService, ConveyorProjectRepo conveyorProjectRepo) {
        this.orderRepo = orderRepo;
        this.userService = userService;
        this.conveyorProjectRepo = conveyorProjectRepo;
    }

    public boolean deleteById(String username, long id) {
        try {
            orderRepo.deleteById(id);
        }
        catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return true;
    }

    public List<Order> getAllUserOrders(String username) {
        return null;
    }

    public Optional<Order> getUserOrderById(String username, long id) {
        Optional<Order> order = orderRepo.findById(id);

        if (order.isPresent()) {
            Object bdUsername = order.get().getConveyorProject().getUser().getUsername();
            return bdUsername.equals(username) ? order : Optional.empty();
        }
        else {
            return Optional.empty();
        }
    }

    public Order save(String username, Order order, long projectId) {
        Optional<ConveyorProject> project = conveyorProjectRepo.findById(projectId);

        if (project.isPresent()) {
            if (project.get().getUser().getUsername().equals(username)) {
                order.setConveyorProject(project.get());
                order.setOrderStatus(new OrderStatus());

                orderRepo.save(order);
            }
            else {
                //need throw
                return null;
            }
        }
        //need throw
        return null;
    }
}
