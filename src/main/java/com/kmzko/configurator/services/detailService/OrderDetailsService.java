package com.kmzko.configurator.services.detailService;

import com.kmzko.configurator.entity.orders.Order;
import com.kmzko.configurator.entity.orders.OrderStatus;
import com.kmzko.configurator.entity.user.ConveyorProject;
import com.kmzko.configurator.repositories.OrderRepo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderDetailsService {
    private final OrderRepo orderRepo;
    private final UserService userService;
    private final ConveyorProjectDetailService conveyorProjectDetailService;

    public OrderDetailsService(OrderRepo orderRepo, UserService userService, ConveyorProjectDetailService conveyorProjectDetailService) {
        this.orderRepo = orderRepo;
        this.userService = userService;
        this.conveyorProjectDetailService = conveyorProjectDetailService;
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
        return userService.findByUsername(username).get().getConveyorProjects().stream().map(ConveyorProject::getOrder).collect(Collectors.toList());
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
        Optional<ConveyorProject> project = conveyorProjectDetailService.getById(projectId);

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
