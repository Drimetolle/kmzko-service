package com.kmzko.configurator.domains.questionnaire;

import com.kmzko.configurator.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "possible_rate_value")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PossibleRateValue extends AbstractEntity {
    private String name;
}
