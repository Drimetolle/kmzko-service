package com.kmzko.configurator.entity.user.conveyor;

import com.kmzko.configurator.domains.AbstractNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "personal_node")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonalNode extends AbstractNode {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "node_id", nullable=false)
    private List<PersonalDetail> details;
}
