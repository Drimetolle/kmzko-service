package com.kmzko.configurator.domains.conveyor;

import com.kmzko.configurator.entity.AbstractConveyor;
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
public class Conveyor extends AbstractConveyor {
    Boolean isTemplate = false;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "conveyor_id", nullable=false)
    List<Node> nodes;
}
