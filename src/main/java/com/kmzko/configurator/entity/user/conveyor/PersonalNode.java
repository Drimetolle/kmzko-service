package com.kmzko.configurator.entity.user.conveyor;

import com.kmzko.configurator.domains.AbstractNode;
import com.kmzko.configurator.domains.conveyor.Detail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personal_node")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonalNode extends AbstractNode {
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "personal_node_detail",
            joinColumns = @JoinColumn(name = "personal_node_id"),
            inverseJoinColumns = @JoinColumn(name = "detail_id")
    )
    private List<Detail> details = new ArrayList<>();;
}
