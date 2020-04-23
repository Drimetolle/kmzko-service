package com.kmzko.configurator.domains;

import com.kmzko.configurator.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String value;

    @NotNull
    private String mark;

    @NotNull
    private String type;
}
