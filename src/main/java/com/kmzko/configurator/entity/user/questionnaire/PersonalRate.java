package com.kmzko.configurator.entity.user.questionnaire;

import com.kmzko.configurator.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "personal_rate")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonalRate extends AbstractEntity {
    @NotNull
    private String name;

    @NotNull
    private String value;

    @NotNull
    private String mark;
}
