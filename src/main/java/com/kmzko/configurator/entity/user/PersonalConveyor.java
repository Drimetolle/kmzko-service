package com.kmzko.configurator.entity.user;

import com.kmzko.configurator.domains.conveyor.ConveyorType;
import com.kmzko.configurator.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "personal_conveyor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonalConveyor extends AbstractEntity {
    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ConveyorType type;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "conveyor_id", nullable=false)
    private List<PersonalNode> nodes;

    @OneToMany(mappedBy = "conveyor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OptionalDetail> optionalDetails;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
