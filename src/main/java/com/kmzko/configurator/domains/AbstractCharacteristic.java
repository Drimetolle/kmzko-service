package com.kmzko.configurator.domains;

import com.kmzko.configurator.domains.conveyor.Characteristic;
import com.kmzko.configurator.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class AbstractCharacteristic extends AbstractEntity {
    @NotNull
    private String name;

    @NotNull
    private String value;

    @NotNull
    private String mark;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "unit_id", nullable=false)
    private Characteristic unit;
}
