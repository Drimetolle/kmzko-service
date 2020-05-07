package com.kmzko.configurator.domains.conveyor;

import com.kmzko.configurator.domains.AbstractDetail;
import com.kmzko.configurator.entity.user.conveyor.PersonalNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "detail")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Detail extends AbstractDetail {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "detail_id", nullable=false)
    private List<Characteristic> characteristics;

    @ManyToMany(mappedBy = "details")
    private Set<Node> nodes = new HashSet<>();

    @ManyToMany(mappedBy = "details")
    private Set<PersonalNode> personalNodes = new HashSet<>();
}
