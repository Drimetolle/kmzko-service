package com.kmzko.configurator.entity.user.questionnaire;

import com.kmzko.configurator.domains.conveyor.ConveyorType;
import com.kmzko.configurator.entity.AbstractEntity;
import com.kmzko.configurator.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "personal_questionnaire")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonalQuestionnaire extends AbstractEntity {
    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ConveyorType type;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "questionnaire_id", nullable=false)
    private List<PersonalRate> rateList;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
