package com.kmzko.configurator.domains.conveyor;

import com.kmzko.configurator.domains.AbstractNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "node")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Node extends AbstractNode {
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "node_detail",
            joinColumns = @JoinColumn(name = "node_id"),
            inverseJoinColumns = @JoinColumn(name = "detail_id")
    )
    private Set<Detail> details = new HashSet<>();
}
