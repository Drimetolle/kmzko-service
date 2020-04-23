package com.kmzko.configurator.entity.user.questionnaire;

import com.kmzko.configurator.domains.AbstractConveyor;
import com.kmzko.configurator.entity.user.User;
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
public class PersonalQuestionnaire extends AbstractConveyor {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "questionnaire_id", nullable=false)
    private List<PersonalRate> rateList;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
