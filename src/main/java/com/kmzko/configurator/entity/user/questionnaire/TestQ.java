package com.kmzko.configurator.entity.user.questionnaire;

import com.kmzko.configurator.domains.questionnaire.Questionnaire;
import com.kmzko.configurator.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personal_questionnaire_test")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TestQ extends AbstractEntity {
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "questionnaire_id", nullable = false)
    private Questionnaire questionnaire;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "questionnaire_id", nullable=false)
    private List<TestP> rateList = new ArrayList<>();
}
