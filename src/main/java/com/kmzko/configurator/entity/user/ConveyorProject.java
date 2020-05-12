package com.kmzko.configurator.entity.user;

import com.kmzko.configurator.entity.AbstractEntity;
import com.kmzko.configurator.entity.orders.Order;
import com.kmzko.configurator.entity.user.conveyor.PersonalConveyor;
import com.kmzko.configurator.entity.user.questionnaire.PersonalQuestionnaire;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "conveyor_projects")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConveyorProject extends AbstractEntity {
    @OneToOne(mappedBy = "conveyorProject", cascade = CascadeType.ALL)
    private PersonalConveyor conveyor;

    @OneToOne(mappedBy = "conveyorProject", cascade = CascadeType.ALL)
    private PersonalQuestionnaire questionnaire;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "conveyorProject", cascade = CascadeType.ALL)
    private Order order;
}
