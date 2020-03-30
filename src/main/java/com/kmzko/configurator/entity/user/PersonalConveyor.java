package com.kmzko.configurator.entity.user;

import com.kmzko.configurator.domains.OptionalDetail;
import com.kmzko.configurator.domains.conveyor.ConveyorType;
import com.kmzko.configurator.domains.conveyor.Node;
import com.kmzko.configurator.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "personal_conveyor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonalConveyor extends AbstractEntity {
    private String name;
    @Enumerated(EnumType.STRING)
    private ConveyorType type;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Node> nodes;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "conveyor", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OptionalDetail> optionalDetails;
}
