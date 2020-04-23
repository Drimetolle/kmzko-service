package com.kmzko.configurator.domains.conveyor;

import com.kmzko.configurator.domains.AbstractNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "node")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Node extends AbstractNode {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "node_id", nullable=false)
    private List<Detail> details;
}
