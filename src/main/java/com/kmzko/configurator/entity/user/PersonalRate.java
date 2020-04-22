package com.kmzko.configurator.entity.user;

import com.kmzko.configurator.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "personal_rate")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonalRate extends AbstractEntity {
    private String name;
    private String value;
    private String mark;
}
