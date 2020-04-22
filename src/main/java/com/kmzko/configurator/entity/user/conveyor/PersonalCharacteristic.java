package com.kmzko.configurator.entity.user.conveyor;

import com.kmzko.configurator.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "personal_characteristic")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonalCharacteristic extends AbstractEntity {
    @NotNull
    private String name;

    private String value;

    @NotNull
    private String mark;

    @NotNull
    private String type;
}
