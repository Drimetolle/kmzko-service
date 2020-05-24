package com.kmzko.configurator.entity.user.questionnaire;

import com.kmzko.configurator.domains.questionnaire.Rate;
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
    private String value;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "rate_id", nullable = false)
    private Rate rate;
}
