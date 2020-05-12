package com.kmzko.configurator.entity.orders;

import com.kmzko.configurator.entity.AbstractEntity;
import com.kmzko.configurator.entity.user.ConveyorProject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order extends AbstractEntity {
    private LocalDateTime expectedDate;

    @ManyToOne
    @JoinColumn(name = "order_status_id", nullable = false)
    private OrderStatus orderStatus;

    @OneToOne
    @JoinColumn(name = "conveyor_project_id", referencedColumnName = "id", nullable = false)
    private ConveyorProject conveyorProject;
}
