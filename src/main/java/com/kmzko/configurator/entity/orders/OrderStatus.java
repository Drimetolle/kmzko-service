package com.kmzko.configurator.entity.orders;

import com.kmzko.configurator.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "order_status")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderStatus extends AbstractEntity {
    private String name;

    @OneToMany(mappedBy = "orderStatus", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Order> orders = new HashSet<>();
}
