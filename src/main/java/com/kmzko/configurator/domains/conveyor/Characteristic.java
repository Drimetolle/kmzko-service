package com.kmzko.configurator.domains.conveyor;

import com.kmzko.configurator.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "characteristic")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Characteristic extends AbstractEntity {
    @NotNull
    private String name;

    private String value;

    @NotNull
    private String mark;

    @NotNull
    private String type;
}
