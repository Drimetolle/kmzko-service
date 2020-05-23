package com.kmzko.configurator.domains.questionnaire;

import com.kmzko.configurator.domains.AbstractRate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rate")
@NoArgsConstructor
@Getter
@Setter
public class Rate extends AbstractRate {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "rate_id", nullable=false)
    private List<PossibleRateValue> possibleRateValues = new ArrayList<>();
}
