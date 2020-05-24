package com.kmzko.configurator.entity.user.questionnaire;

import com.kmzko.configurator.domains.questionnaire.Rate;
import com.kmzko.configurator.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "personal_rate_test")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TestP extends AbstractEntity {
    private String value;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "rate_id", nullable = false)
    private Rate rate;
}
