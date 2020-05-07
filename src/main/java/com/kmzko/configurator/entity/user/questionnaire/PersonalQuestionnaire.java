package com.kmzko.configurator.entity.user.questionnaire;

import com.kmzko.configurator.domains.AbstractConveyor;
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
public class PersonalQuestionnaire extends AbstractConveyor {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "questionnaire_id", nullable=false)
    private List<PersonalRate> rateList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "conveyor_project_id", referencedColumnName = "id", nullable = false)
    private ConveyorProject conveyorProject;
}
