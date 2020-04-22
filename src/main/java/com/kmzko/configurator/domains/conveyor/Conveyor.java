package com.kmzko.configurator.domains.conveyor;

import com.kmzko.configurator.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "conveyor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Conveyor extends AbstractEntity {
    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ConveyorType type;

    private Boolean isTemplate = false;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "conveyor_id", nullable=false)
    private List<Node> nodes;

    public Conveyor(String name, List<Node> nodes) {
        this.name = name;
        this.nodes = nodes;
    }
}
