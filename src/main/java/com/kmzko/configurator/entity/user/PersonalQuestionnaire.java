package com.kmzko.configurator.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kmzko.configurator.domains.conveyor.ConveyorType;
import com.kmzko.configurator.domains.questionnaire.Rate;
import com.kmzko.configurator.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "personal_questionnaire")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonalQuestionnaire extends AbstractEntity {
    private String name;
    @Enumerated(EnumType.STRING)
    private ConveyorType type;
    @Column(insertable = false, updatable = false)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rate> rateList;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
