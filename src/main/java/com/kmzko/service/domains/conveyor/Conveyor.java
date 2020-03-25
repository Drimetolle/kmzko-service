package com.kmzko.service.domains.conveyor;

import com.kmzko.service.domains.ConveyorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "conveyor")
public class Conveyor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ConveyorType type;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Node> nodes;

    public Conveyor(String name, List<Node> nodes) {
        this.name = name;
        this.nodes = nodes;
    }
}
