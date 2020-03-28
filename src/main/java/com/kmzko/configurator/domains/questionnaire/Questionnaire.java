package com.kmzko.configurator.domains.questionnaire;

import com.kmzko.configurator.domains.conveyor.ConveyorType;
import com.kmzko.configurator.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questionnaire")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Questionnaire extends AbstractEntity {
    private String name;
    @Enumerated(EnumType.STRING)
    private ConveyorType type;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rate> rateList;

    public Questionnaire(List<Rate> rateList, ConveyorType type) {
        this.rateList = rateList;
        this.type = type;
    }
}
