package com.kmzko.configurator.entity.user.conveyor;

import com.kmzko.configurator.domains.AbstractConveyor;
import com.kmzko.configurator.entity.user.ConveyorProject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personal_conveyor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonalConveyor extends AbstractConveyor {
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "conveyor_id", nullable=false)
    private List<PersonalNode> nodes = new ArrayList<>();

    @OneToMany(mappedBy = "conveyor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OptionalDetail> optionalDetails = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "conveyor_project_id", referencedColumnName = "id", nullable = false)
    private ConveyorProject conveyorProject;
}
