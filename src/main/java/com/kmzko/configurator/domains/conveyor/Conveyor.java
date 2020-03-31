package com.kmzko.configurator.domains.conveyor;

import com.kmzko.configurator.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "conveyor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Conveyor extends AbstractEntity {
    private String name;
    @Enumerated(EnumType.STRING)
    private ConveyorType type;
    private Boolean isTemplate = false;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Node> nodes;

    public Conveyor(String name, List<Node> nodes) {
        this.name = name;
        this.nodes = nodes;
    }
}
