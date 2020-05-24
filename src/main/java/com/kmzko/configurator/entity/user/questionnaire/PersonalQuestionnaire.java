package com.kmzko.configurator.entity.user.questionnaire;

import com.kmzko.configurator.domains.questionnaire.Questionnaire;
import com.kmzko.configurator.entity.AbstractEntity;
import com.kmzko.configurator.entity.user.ConveyorProject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personal_questionnaire")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonalQuestionnaire extends AbstractEntity {
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "questionnaire_id", nullable = false)
    private Questionnaire questionnaire;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "questionnaire_id", nullable=false)
    private List<PersonalRate> rateList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "conveyor_project_id", referencedColumnName = "id", nullable = false)
    private ConveyorProject conveyorProject;
}
