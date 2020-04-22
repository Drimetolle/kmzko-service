package com.kmzko.configurator.entity;

import com.kmzko.configurator.domains.conveyor.ConveyorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class AbstractConveyor extends AbstractEntity {
    @NotNull
    String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    ConveyorType type;
}
