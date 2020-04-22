package com.kmzko.configurator.entity;

import com.kmzko.configurator.domains.conveyor.ConveyorType;
import com.kmzko.configurator.domains.conveyor.Node;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AbstractConveyor extends AbstractEntity  {
    @NotNull
    String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    ConveyorType type;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "conveyor_id", nullable=false)
    List<Node> nodes;
}
